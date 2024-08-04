package com.example.pokemon.repository

import com.example.pokemon.data.ApiService
import com.example.pokemon.utility.safeApiCall
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPokemonList(offset: Int, limit: Int)= safeApiCall {
         apiService.getPokemonList(offset, limit)
    }

    suspend fun getPokemonDetails(id : String) = safeApiCall {
        apiService.getPokemonDetails(id)
    }
}