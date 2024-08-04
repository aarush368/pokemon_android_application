package com.example.pokemon.view.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokemon.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val RobotoBold = FontFamily(
    Font(R.font.roboto_bold , FontWeight.Bold)
)

val RobotoMedium = FontFamily(
    Font(R.font.roboto_medium , FontWeight.Bold)
)

val RobotoRegular = FontFamily(
    Font(R.font.roboto_regular , FontWeight.Bold)
)