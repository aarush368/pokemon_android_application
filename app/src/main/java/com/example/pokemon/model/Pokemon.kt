package com.example.pokemon.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Pokemon (
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
