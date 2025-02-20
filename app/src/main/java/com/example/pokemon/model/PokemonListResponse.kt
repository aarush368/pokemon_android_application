package com.example.pokemon.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PokemonListResponse(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Pokemon> = arrayListOf()

)
