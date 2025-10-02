package com.example.rickandmorty.ui.theme.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.rickandmorty.ui.theme.data.room.AppDatabase
import com.example.rickandmorty.ui.theme.data.room.CharacterDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Repository som håndterer API-forespørsler og lokal lagring for karakterdata
object CharacterRepository {

    // Database og DAO for lagring av karakterer
    private lateinit var appDatabase: AppDatabase
    private lateinit var characterDao: CharacterDao

    // HTTP-klient med logging for debugging
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    // Retrofit-instans for API-forespørsler
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://rickandmortyapi.com/api/")  // Base URL for APIet
        .addConverterFactory(GsonConverterFactory.create())  // JSON til objekt-konvertering
        .build()

    // CharacterService for å gjøre API-forespørsler
    private val characterService: CharacterService = retrofit.create(CharacterService::class.java)


    // Initialiserer databasen og DAO-en for lokal lagring
    fun initializeDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context)
        characterDao = appDatabase.characterDao()
    }

    // Henter alle karakterene fra APIet, side for side
    suspend fun getAllCharacters(): List<Character> {
        val charactersList = mutableListOf<Character>()
        var currentPage = 1

        while (true) {
            try {
                // Forespør API for karakterer på gjeldende side
                val response = characterService.getCharacters(page = currentPage)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.characters.isNotEmpty()) {
                        Log.d(
                            "CharacterRepository",
                            "Fetched ${body.characters.size} characters from page $currentPage"
                        )

                        // Legger karakterene til listen og lagrer dem i databasen hvis de ikke er der fra før
                        body.characters.forEach { apiCharacter ->
                            val character = Character(
                                id = apiCharacter.id,
                                name = apiCharacter.name,
                                status = apiCharacter.status,
                                species = apiCharacter.species,
                                gender = apiCharacter.gender,
                                image = apiCharacter.image,
                                isUserCreated = false
                            )
                            charactersList.add(character)
                            if (getCharacterById(character.id) == null) {
                                insertCharacter(character)
                            }
                        }

                        // Sjekker om det finnes en neste side, ellers stopper loopen
                        if (body.info.next == null) break
                        currentPage++
                    } else {
                        Log.e("CharacterRepository", "Response body is null or empty")
                        break
                    }
                } else {
                    Log.e(
                        "CharacterRepository",
                        "Failed to fetch characters, status code: ${response.code()}"
                    )
                    break
                }
            } catch (e: Exception) {
                Log.e("CharacterRepository", "Error fetching characters", e)
                return emptyList()
            }
        }

        Log.d("CharacterRepository", "Total API characters fetched: ${charactersList.size}")
        return charactersList
    }

    // Setter inn karakterer i den lokale databasen
    suspend fun insertCharacter(character: Character) {
        try {
            characterDao.insertCharacter(character)
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error inserting character into database", e)
        }
    }

    // Henter en karakter etter ID fra den lokale databasen
    suspend fun getCharacterById(characterId: Int): Character? {
        return characterDao.getCharacterById(characterId)
    }

    // Henter alle lagrede karakterer fra databasen
    suspend fun getSavedCharacters(): List<Character> {
        return characterDao.getAllCharacters()
    }

    // Setter en karakter som favoritt i databasen
    suspend fun setFavorite(characterId: Int, isFavorite: Boolean) {
        characterDao.updateFavoriteStatus(characterId, isFavorite)
    }

    // Legger til en notat til en karakter
    suspend fun addNoteToCharacter(characterId: Int, note: String?) {
        try {
            characterDao.updateNote(characterId, note)
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error adding note to character", e)
        }
    }

    // Henter favorittkarakterer som LiveData
    fun getFavoriteCharacters(): LiveData<List<Character>> {
        return characterDao.getFavoriteCharacters()
    }
}

