package com.example.rickandmorty.ui.theme.screens.screens.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.ui.theme.data.Character
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _characterState = MutableStateFlow<Character?>(null)
    val characterState: StateFlow<Character?> = _characterState

    fun loadCharacter(characterId: Int) {
        viewModelScope.launch {
            _loading.value = true
            val character = characterRepository.getCharacterById(characterId)
            _characterState.value = character
            _loading.value = false
        }
    }

    fun addNoteToCharacter(note: String) {
        viewModelScope.launch {
            characterState.value?.let { character ->
                characterRepository.addNoteToCharacter(character.id, note)
                loadCharacter(character.id)
            }
        }
    }
}

