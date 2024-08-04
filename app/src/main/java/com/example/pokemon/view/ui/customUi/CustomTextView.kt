package com.example.pokemon.view.ui.customUi

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun TextBig(
    msg : String,
    modifier: Modifier
){
    Text(
        text = msg,
        modifier = modifier,
        fontSize = 24.sp,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun TextRegular(
    msg : String,
    modifier: Modifier
){
    Text(
        text = msg,
        modifier = modifier,
        fontSize = 18.sp,
        color = MaterialTheme.colors.onPrimary
    )
}