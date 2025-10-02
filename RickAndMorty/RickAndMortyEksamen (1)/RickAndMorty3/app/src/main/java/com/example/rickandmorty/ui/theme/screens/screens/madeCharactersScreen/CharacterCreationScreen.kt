package com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rickandmorty.ui.theme.data.Character

@Composable
fun CharacterCreationScreen(
    viewModel: SavedCharactersViewModel = viewModel(), // ViewModel for lagrede karakterer
    onCharacterCreated: () -> Unit,
    navController: NavHostController
) {
    // Tekstfeltene for karakterinfo
    var characterName by remember { mutableStateOf("") }
    var characterSpecies by remember { mutableStateOf("") }
    var characterStatus by remember { mutableStateOf("") }
    var characterGender by remember { mutableStateOf("") }

    // Variabel for feilmelding
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Layout for skjerm
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Tekstfelter for input av bruker
        TextField(
            value = characterName,
            onValueChange = { characterName = it },
            label = { Text("Character Name") }
        )
        TextField(
            value = characterSpecies,
            onValueChange = { characterSpecies = it },
            label = { Text("Species") }
        )
        TextField(
            value = characterStatus,
            onValueChange = { characterStatus = it },
            label = { Text("Status") }
        )
        TextField(
            value = characterGender,
            onValueChange = { characterGender = it },
            label = { Text("Gender") }
        )

        // Knapp for å lage karakter
        Button(
            onClick = {
                // Valider input før oppretting
                if (characterName.isNotEmpty() && characterSpecies.isNotEmpty() &&
                    characterStatus.isNotEmpty() && characterGender.isNotEmpty()
                ) {
                    val newCharacter = Character(
                        id = 0,
                        name = characterName,
                        species = characterSpecies,
                        status = characterStatus,
                        gender = characterGender,
                        isUserCreated = true
                    )
                    viewModel.addSavedCharacter(newCharacter) // Legger til karakter
                    onCharacterCreated()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00FF00),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Create Character")
        }

        // Tilbake-knapp
        Button(
            onClick = {
                navController.popBackStack("character_list", inclusive = false)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF39FF14),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Back",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}



