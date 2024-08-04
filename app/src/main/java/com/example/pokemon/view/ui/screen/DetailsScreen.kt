package com.example.pokemon.view.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemon.utility.BackPressHandler
import com.example.pokemon.view.ui.customUi.Toolbar
import com.example.pokemon.view.ui.theme.RobotoBold
import com.example.pokemon.view.ui.theme.RobotoRegular
import com.example.pokemon.viewModel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    bundle: Bundle?
) {
    LaunchedEffect(key1 = Unit) {
        bundle?.getString("id")?.let { pokemonViewModel.callPokemonDetailsApi(it) }
    }
    val imageUrl = bundle?.getString("imageUrl").toString()

    BackPressHandler(
        onBackPressed = {
            navController.popBackStack()
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                onClickNavigate = {
                    navController.popBackStack()
                },
                headingName = "Pokemon"
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    if (pokemonViewModel.pokemonDetailsData != null) {
                        Image(
                            painter = rememberImagePainter(data = imageUrl),
                            contentDescription = "Charmander",
                            modifier = Modifier.size(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = pokemonViewModel.pokemonDetailsData?.name.toString()
                                .toUpperCase(),
                            style = typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = androidx.compose.material.MaterialTheme.colors.onPrimary,
                            )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            pokemonViewModel.pokemonDetailsData?.let {
                                AttributeRow("Weight", it.weight.toString())
                                AttributeRow("Height", it.height.toString())
                                AttributeRow("Order", it.order.toString())
                                AttributeRow("Species", it.species?.name.toString())
                            }
                        }
                    } else {
                        Text(
                            text = "Unable to fetch Pokemon Drtails",
                            style = typography.h4,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun AttributeRow(attribute: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = attribute,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = RobotoBold,
            color = androidx.compose.material.MaterialTheme.colors.onPrimary,

            )
        Text(
            text = value,
            fontSize = 18.sp,
            fontFamily = RobotoRegular,
            color = androidx.compose.material.MaterialTheme.colors.onPrimary,
            )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
