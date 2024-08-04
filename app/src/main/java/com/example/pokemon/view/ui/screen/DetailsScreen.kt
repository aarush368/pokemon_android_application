package com.example.pokemon.view.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemon.model.Abilities
import com.example.pokemon.model.PokemonDetailsResponse
import com.example.pokemon.model.Species
import com.example.pokemon.view.ui.customUi.CustomProgressBar
import com.example.pokemon.view.ui.customUi.TextBig
import com.example.pokemon.view.ui.customUi.TextRegular
import com.example.pokemon.view.ui.customUi.Toolbar
import com.example.pokemon.viewModel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    bundle: Bundle?,
) {
    LaunchedEffect(key1 = Unit) {
        bundle?.getString("id")?.let { pokemonViewModel.callPokemonDetailsApi(it) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                onClickNavigate = {
                    navController.popBackStack()
                },
                headingName = "POKEMON DETAILS"
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (pokemonViewModel.isLoading.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)), // Optional: adds a semi-transparent background
                        contentAlignment = Alignment.Center
                    ) {
                        CustomProgressBar()
                    }
                } else {
                    if (pokemonViewModel.pokemonDetailsData == null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center) // Center the Text in the Box
                        ) {
                            Text(
                                text = "Unable to fetch Data",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center) // Center the Text in the Box
                        ) {
                            PokemonDetailsScreen(pokemonDetails = pokemonViewModel.pokemonDetailsData!!)
                        }
                    }
                }
            }

        }
    )
}


@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetailsResponse) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            TextSection(title = "Name", value = pokemonDetails.name)
            TextSection(title = "Base Experience", value = pokemonDetails.baseExperience.toString())
            TextSection(title = "Height", value = pokemonDetails.height.toString())
            TextSection(title = "Weight", value = pokemonDetails.weight.toString())
            TextSection(title = "Order", value = pokemonDetails.order.toString())
            TextSection(
                title = "Location Area Encounters",
                value = pokemonDetails.locationAreaEncounters
            )
            TextSection(title = "Species", value = pokemonDetails.species?.name)
            AbilitiesSection(abilities = pokemonDetails.abilities)
        }
    }
}

@Composable
fun TextSection(title: String, value: String?) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        TextBig(
            msg = title,
            modifier = Modifier
        )
        TextRegular(
            msg = value ?: "N/A",
            modifier = Modifier
        )
    }
}

@Composable
fun AbilitiesSection(abilities: List<Abilities>) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        TextBig(
            msg = "Abilities",
            modifier = Modifier
        )
        abilities.forEach { ability ->
            TextRegular(
                msg = ability.name ?: "N/A",
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsScreenPreview() {
    val sampleData = PokemonDetailsResponse(
        abilities = arrayListOf(
            Abilities(name = "Overgrow", url = ""),
            Abilities(name = "Chlorophyll", url = "")
        ),
        baseExperience = "64",
        height = "7",
        id = "1",
        locationAreaEncounters = "Kanto",
        name = "Bulbasaur",
        order = "1",
        species = Species(name = "Bulbasaur", url = ""),
        weight = "69"
    )

    PokemonDetailsScreen(pokemonDetails = sampleData)
}
