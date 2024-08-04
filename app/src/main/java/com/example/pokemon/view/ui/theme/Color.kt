package com.example.pokemon.view.ui.theme

import androidx.compose.ui.graphics.Color
val Text_Green = Color(0xFF387A29)
val Text_Green2 = Color(0xDD4BA536)

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
) {
    object Night : ThemeColors(
        background = Color(0xFF000000),
        surface = Color(0xFF000000),
        primary =Color(0xFF000000),
        text = Color(0xFFFFFFFF),
    )

    object Day : ThemeColors(
        background = Color(0xFFFFFFFF),
        surface = Color(0xFFFFFFFF),
        primary = Color(0xFFFFFFFF),
        text = Color(0xFF000000),
    )
}

