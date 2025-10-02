package com.example.rickandmorty.ui.theme.screens.screens.favorite_characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import com.example.rickandmorty.ui.theme.screens.screens.character_list.CharacterItem
import com.example.rickandmorty.ui.theme.theme.DeepCharcoal

@Composable
fun FavoriteCharactersScreen(
    navController: NavHostController,
    characterRepository: CharacterRepository
) {
    // Henter ViewModel
    val viewModel: FavoriteCharactersViewModel = remember(characterRepository) {
        FavoriteCharactersViewModel(characterRepository)
    }

    //Viser favorittkarakterene
    val favorites by viewModel.favoriteCharacters.observeAsState(emptyList())

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(DeepCharcoal),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tittel
            Text(
                text = "Favorittkarakterer",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Ved ingen favoritter logges en melding
            if (favorites.isEmpty()) {
                Text(
                    "No favortie characters",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            } else {
                // Viser eventuelle favoritter
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(favorites) { character ->
                        CharacterItem(
                            character = character,
                            onClick = { navController.navigate("character_details/${character.id}") },
                            onFavoriteClick = { viewModel.toggleFavorite(character.id) },
                            isFavorite = character.isFavorite
                        )
                    }
                }
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
}
