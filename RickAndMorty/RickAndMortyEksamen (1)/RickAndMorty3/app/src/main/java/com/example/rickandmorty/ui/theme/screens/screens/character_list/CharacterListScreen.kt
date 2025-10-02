package com.example.rickandmorty.ui.theme.screens.screens.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.theme.theme.DeepCharcoal
import com.example.rickandmorty.ui.theme.theme.NeonGreen

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel,
    onCharacterClick: (Int) -> Unit,
) {
    val loading by viewModel.loading.collectAsState()  // Henter loading state
    val characters by viewModel.charactersState.collectAsState()  // Henter liste av karakterer
    val favorites by viewModel.favorites.collectAsState()  // Henter liste av favoritter

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()  // Laster inn karakterer ved første oppstart
    }

    if (loading) {
        // Vises når karakterene lastes
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = NeonGreen)  // Lastespinner ved loading
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = DeepCharcoal),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (characters.isEmpty()) {
            // Vises om ingen karakterer finnes
            Text(
                "No characters found",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
        } else {
            // Grid for å vise karakterene
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(characters) { character ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.Gray, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                            .clickable { onCharacterClick(character.id) }  // Gjør at karakteren blir klikkbar
                    ) {

                        // Viser bilde av karakteren
                        AsyncImage(
                            model = character.image,
                            contentDescription = "Character Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp)
                        )

                        // Viser navn på karakteren
                        Text(
                            text = character.name,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                        )

                        // Ikon for å favorisere/avfavorisere karakteren
                        IconButton(
                            onClick = { viewModel.toggleFavorite(character) },
                            modifier = Modifier
                                .align(Alignment.End)
                                .background(
                                    color = if (favorites.contains(character)) NeonGreen else Color.Transparent,
                                    shape = androidx.compose.foundation.shape.CircleShape
                                )
                                .then(
                                    if (!favorites.contains(character)) Modifier.border(
                                        width = 2.dp,
                                        color = DeepCharcoal,
                                        shape = androidx.compose.foundation.shape.CircleShape
                                    ) else Modifier
                                )
                        ) {
                            Icon(
                                imageVector = if (favorites.contains(character)) {
                                    androidx.compose.material.icons.Icons.Filled.Star
                                } else {
                                    androidx.compose.material.icons.Icons.Filled.StarBorder
                                },
                                contentDescription = if (favorites.contains(character)) "Unfavorite" else "Favorite",
                                tint = if (favorites.contains(character)) Color.Black else NeonGreen
                            )
                        }
                    }
                }
            }
        }
    }
}
