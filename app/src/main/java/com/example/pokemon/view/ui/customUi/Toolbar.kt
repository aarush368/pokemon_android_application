package com.example.pokemon.view.ui.customUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.view.graph.NavGraphRoute
import com.example.pokemon.view.ui.theme.RobotoMedium

@Composable
fun Toolbar(
    onClickNavigate: () -> Unit,
    headingName: String
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.onPrimary),
        title = {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onClickNavigate) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    TextBold(
                        msg = headingName,
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(modifier = Modifier.width(36.dp)) // Placeholder for symmetry
                }
            }
        },
        elevation = 10.dp
    )
}
