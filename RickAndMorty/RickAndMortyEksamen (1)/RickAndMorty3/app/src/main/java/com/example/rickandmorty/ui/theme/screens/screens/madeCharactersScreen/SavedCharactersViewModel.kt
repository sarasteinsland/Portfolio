package com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.rickandmorty.ui.theme.data.Character
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import kotlinx.coroutines.Dispatchers

// ViewModel for håndtering av lagrede karakterer
class SavedCharactersViewModel(
    private val repository: CharacterRepository // Referanse til databasen/repositoryet
) : ViewModel() {

    // MutableStateFlow for å holde listen over lagrede karakterer
    private val _savedCharacters = MutableStateFlow<List<Character>>(emptyList())
    val savedCharacters: StateFlow<List<Character>> = _savedCharacters // Eksponerer en StateFlow (kun lesbar)

    // MutableStateFlow for å holde statusen for lasting
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading // Eksponerer en StateFlow (kun lesbar)

    init {
        loadSavedCharacters() // Laster lagrede karakterer ved oppstart
    }

    // Funksjon for å laste lagrede karakterer fra databasen
    private fun loadSavedCharacters() {
        viewModelScope.launch { // Kjører i ViewModelScope for å håndtere korutiner
            _loading.value = true // Setter lastestatus til true
            try {
                // Henter kun karakterer som er opprettet av brukeren
                _savedCharacters.value = repository.getSavedCharacters().filter { it.isUserCreated }
            } catch (e: Exception) {
                Log.e("LoadSavedCharacters", "Fail loading character", e)
            } finally {
                _loading.value = false // Setter lastestatus til false
            }
        }
    }

    // Funksjon for å legge til en ny lagret karakter
    fun addSavedCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) { // Kjører i IO-dispatcher for bakgrunnsarbeid
            try {
                // Oppretter en kopi av karakteren med flagg som indikerer at den er brukeropprettet
                val characterWithFlag = character.copy(isUserCreated = true)
                repository.insertCharacter(characterWithFlag) // Lagrer karakteren i databasen
                loadSavedCharacters() // Oppdaterer listen med lagrede karakterer
            } catch (e: Exception) {
                Log.e("SavedCharactersViewModel", "Error saving character", e)
            }
        }
    }
}

