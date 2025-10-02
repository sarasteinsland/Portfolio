package com.example.rickandmorty.ui.theme.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Interface for å hente karakterdata fra APIet
interface CharacterService {

    // GET-forespørsel til "character" endpointet i APIet
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int  // Tar inn en query-parameter for å spesifisere hvilken side av karakterene som skal hentes
    ): Response<CharacterApiResponse>  // Returnerer en Response med en liste av karakterer
}
