package com.example.rickandmorty.ui.theme.screens.screens.character_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.ui.theme.data.Character
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(private val repository: CharacterRepository) : ViewModel() {

    // StateFlow for karakterne som blir hentet fra API og database
    private val _charactersState = MutableStateFlow<List<Character>>(emptyList())
    val charactersState: StateFlow<List<Character>> = _charactersState

    // StateFlow for favoritt-karakterne
    private val _favorites = MutableStateFlow<List<Character>>(emptyList())
    val favorites: StateFlow<List<Character>> = _favorites

    // StateFlow for lasting av data
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        loadCharacters() // Laster inn karakterene når ViewModel opprettes
    }

    // Funksjon for å laste karakterer fra API og lokal database
    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val characters = repository.getAllCharacters()
            _charactersState.value = characters
            _loading.value = false
        }
    }

    // Funksjon for å endre favorittstatus på en karakter
    fun toggleFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Oppdaterer favorittstatus i databasen
                repository.setFavorite(character.id, !character.isFavorite)
            } catch (e: Exception) {
                Log.e("CharacterListViewModel", "Feil ved toggling av favoritt", e)
            }
        }

        // Oppdaterer favorittstatus lokalt i _charactersState
        _charactersState.value = _charactersState.value.map {
            if (it.id == character.id) it.copy(isFavorite = !character.isFavorite) else it
        }

        // Filtrerer favoritt-karakterene og oppdaterer _favorites
        _favorites.value = _charactersState.value.filter { it.isFavorite }
    }
}


