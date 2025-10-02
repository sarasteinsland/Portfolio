package com.example.rickandmorty.ui.theme.screens.screens.favorite_characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.ui.theme.data.Character
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _favoriteCharacters = MutableLiveData<List<Character>>() // Holder favorittkarakterer
    val favoriteCharacters: LiveData<List<Character>> = _favoriteCharacters // Observerbart for UI

    init {
        fetchFavoriteCharacters() // Henter favoritter ved oppstart
    }

    private fun fetchFavoriteCharacters() {
        // Observerer endringer i favorittlisten
        characterRepository.getFavoriteCharacters().observeForever { characters ->
            _favoriteCharacters.value = characters
        }
    }

    fun toggleFavorite(characterId: Int) {
        viewModelScope.launch {
            try {
                // Finner karakter og oppdater favorittstatus
                val currentCharacter = _favoriteCharacters.value?.find { it.id == characterId }
                currentCharacter?.let {
                    val updatedCharacter = it.copy(isFavorite = !it.isFavorite) // Endrer favorittstatus
                    characterRepository.setFavorite(updatedCharacter.id, updatedCharacter.isFavorite)
                    fetchFavoriteCharacters() // Oppdaterer listen
                }
            } catch (e: Exception) {
                Log.e("FavoriteCharactersVM", "Error toggling favorite status", e) // Logger feil
            }
        }
    }
}
