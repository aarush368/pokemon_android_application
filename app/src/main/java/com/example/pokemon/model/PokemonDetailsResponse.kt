package com.example.pokemon.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    @SerializedName("abilities"                ) var abilities              : ArrayList<Abilities>   = arrayListOf(),
    @SerializedName("base_experience"          ) var baseExperience         : String?                   = null,
    @SerializedName("height"                   ) var height                 : String?                   = null,
    @SerializedName("id"                       ) var id                     : String?                   = null,
    @SerializedName("location_area_encounters" ) var locationAreaEncounters : String?                = null,
    @SerializedName("name"                     ) var name                   : String?                = null,
    @SerializedName("order"                    ) var order                  : String?                   = null,
    @SerializedName("species"                  ) var species                : Species?               = Species(),
    @SerializedName("weight"                   ) var weight                 : String?                   = null
)


data class Abilities(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)

data class Species(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)