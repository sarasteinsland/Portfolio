package com.example.rickandmorty.ui.theme.screens.screens.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.theme.theme.DeepCharcoal
import com.example.rickandmorty.ui.theme.theme.RickAndMortyTheme

@Composable
fun CharacterDetailsScreen(
    onBackButtonClick: () -> Unit,  // Håndterer tilbakeknappen
    viewModel: CharacterDetailsViewModel  // ViewModel for karakterdetaljer
) {
    val loading by viewModel.loading.collectAsState()  // Henter lastestatus
    val characterState by viewModel.characterState.collectAsState()  // Henter karakterdata

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()  // Viser spinner mens man venter på data
        }
        return
    }

    val character = characterState
    if (character == null) {  // Hvis karakterdata er null
        Text(text = "Failed to get character details. Character object is NULL!")
        return
    }

    RickAndMortyTheme {
        Box(
            modifier = Modifier.fillMaxSize()  // Fullskjerm bakgrunn
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = DeepCharcoal),  // Bakgrunnsbilde
                model = character.image,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Image of ${character.name}"
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Øverste rad med tilbakeknapp og tittel
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onBackButtonClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Character Details",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                // Karakterdetaljer og notat
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 24.dp)
                        .verticalScroll(state = rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(32.dp))

                    // Karakterinformasjon
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Status: ${character.status}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Species: ${character.species}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Gender: ${character.gender}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Notatfelt for karakter
                    var note by remember { mutableStateOf(character.note ?: "") }

                    TextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Add a note") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Lagre knapp for notatet
                    Button(
                        onClick = {
                            viewModel.addNoteToCharacter(note)  // Legger til notat i ViewModel
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF39FF14),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Save Note")
                    }
                }
            }
        }
    }
}
