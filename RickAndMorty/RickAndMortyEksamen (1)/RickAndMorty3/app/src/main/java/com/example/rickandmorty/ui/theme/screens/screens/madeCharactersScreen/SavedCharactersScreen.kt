package com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickandmorty.ui.theme.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.theme.SteelGray

@Composable
fun SavedCharactersScreen(
    viewModel: SavedCharactersViewModel, // ViewModel for lagrede karakterer
    navController: NavController // For navigasjon
) {
    val savedCharacters = viewModel.savedCharacters.collectAsState() // Laster karakterer

    RickAndMortyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Created Characters", // Overskrift
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            if (savedCharacters.value.isEmpty()) {
                Text(
                    text = "No characters created yet", // Melding som vises om man ikke har lagret karakter enda
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(savedCharacters.value) { character -> // Viser hver karakter
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.Gray)
                                .padding(8.dp)
                        ) {
                            Text(text = character.name, color = Color.White)
                            Text(text = "Species: ${character.species}", color = SteelGray)
                            Text(text = "Status: ${character.status}", color = SteelGray)
                            Text(text = "Gender: ${character.gender}", color = SteelGray)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Lager utseende og tekst for knapp tilbake til forside
            Button(
                onClick = { navController.popBackStack("character_list", false) }, // Tilbakeknapp
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF39FF14),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Back")
            }
        }
    }
}
