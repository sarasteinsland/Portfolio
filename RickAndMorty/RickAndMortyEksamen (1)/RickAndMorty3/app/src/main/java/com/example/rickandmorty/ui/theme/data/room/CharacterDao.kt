package com.example.rickandmorty.ui.theme.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.ui.theme.data.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)

    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): Character?

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<Character>

    @Query("UPDATE character SET isFavorite = :isFavorite WHERE id = :characterId")
    suspend fun updateFavoriteStatus(characterId: Int, isFavorite: Boolean)

    @Query("UPDATE character SET note = :note WHERE id = :characterId")
    suspend fun updateNote(characterId: Int, note: String?)

    @Query("SELECT * FROM character WHERE isFavorite = 1")
    fun getFavoriteCharacters(): LiveData<List<Character>>
}



