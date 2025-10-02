package com.example.rickandmorty.ui.theme.screens.screens.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.theme.data.Character
import androidx.compose.foundation.clickable
import com.example.rickandmorty.ui.theme.theme.DeepCharcoal
import com.example.rickandmorty.ui.theme.theme.NeonGreen

@Composable
fun CharacterItem(
    character: Character,
    onClick: () -> Unit,  // Klikk på karakter
    onFavoriteClick: () -> Unit,  // Klikk på favorittikon
    isFavorite: Boolean  // Sjekker om karakter er valgt som favoritt
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Karakterbilde
        AsyncImage(
            model = character.image,
            contentDescription = "Character Image",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 16.dp)
        )

        // Karakterens navn
        Text(
            text = character.name,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        // Favoritt-ikon
        IconButton(
            onClick = { onFavoriteClick() },  // Håndter favorittklikk
            modifier = Modifier
                .background(
                    color = if (isFavorite) NeonGreen else Color.Transparent,
                    shape = androidx.compose.foundation.shape.CircleShape
                )
                .then(
                    if (!isFavorite) Modifier.border(
                        width = 2.dp,
                        color = DeepCharcoal,
                        shape = androidx.compose.foundation.shape.CircleShape
                    ) else Modifier
                )
        ) {
            // Viser stjerne ikon ettersom om de er favoritt eller ikke
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                tint = if (isFavorite) Color.Black else NeonGreen
            )
        }
    }
}
