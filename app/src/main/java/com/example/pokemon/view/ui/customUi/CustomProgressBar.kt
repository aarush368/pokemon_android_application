package com.example.pokemon.view.ui.customUi

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemon.view.ui.theme.Text_Green
import com.example.pokemon.view.ui.theme.Text_Green2

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 600
            }
        ),
        label = ""
    )
    CircularProgressIndicator(
        progress = 1f,
        modifier = modifier
            .size(50.dp)
            .rotate(angle)
            .border(
                5.dp,
                brush = Brush.sweepGradient(
                    listOf(
                        Color.White,
                        Text_Green.copy(alpha = 0.1f),
                        Text_Green2
                    )
                ),
                shape = CircleShape
            ),
        strokeWidth = 1.dp,
        color = Color.White
    )

}