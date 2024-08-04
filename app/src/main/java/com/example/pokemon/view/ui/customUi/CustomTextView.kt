package com.example.pokemon.view.ui.customUi

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pokemon.view.ui.theme.RobotoBold

@Composable
fun TextBold(
    msg : String,
    modifier: Modifier
){
    Text(
        text = msg,
        modifier = modifier,
        fontSize = 36.sp,
        color = MaterialTheme.colors.onPrimary,
        fontFamily = RobotoBold,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
    )
}

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