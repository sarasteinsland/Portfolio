package com.example.rickandmorty.ui.theme.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// API-respons med informasjon og liste over karakterer
data class CharacterApiResponse(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val characters: List<Character>
)

// Metadata om API-responsen
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

// Karakterdata som lagres i databasen
@Entity(tableName = "character")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String = "unknown",
    val species: String = "unknown",
    val gender: String = "unknown",
    val image: String = "",
    val isUserCreated: Boolean = false,
    var isFavorite: Boolean = false,
    var note: String? = null
)
