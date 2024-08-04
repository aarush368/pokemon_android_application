package com.example.pokemon.data

import com.example.pokemon.utility.ApiConstants
import com.example.pokemon.model.PokemonDetailsResponse
import com.example.pokemon.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 * This ia an interface for the api end-points
 */

interface ApiService {

    @GET(ApiConstants.POKEMON_LIST_END_URL_API)
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonListResponse>

    @GET("${ApiConstants.POKEMON_DETAILS_END_URL_API}/{id}/")
    suspend fun getPokemonDetails(@Path("id") id: String): Response<PokemonDetailsResponse>

}