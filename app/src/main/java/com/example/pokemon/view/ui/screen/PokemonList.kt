package com.example.pokemon.view.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemon.utility.NetworkStatus
import com.example.pokemon.view.graph.NavGraphRoute
import com.example.pokemon.model.Pokemon
import com.example.pokemon.view.ui.customUi.CustomProgressBar
import com.example.pokemon.view.ui.customUi.TextBig
import com.example.pokemon.view.ui.customUi.Toolbar
import com.example.pokemon.viewModel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PokemonListScreen(
    pokemonViewModel: PokemonViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = pokemonViewModel.hasLoaded.value) {
        if (pokemonViewModel.hasLoaded.value){
            pokemonViewModel.callPokemonListApi(pokemonViewModel.offset, 20)
        }
    }

    val pokemonListResponse by pokemonViewModel.pokemonListResponse.collectAsState()

    LaunchedEffect(key1 = pokemonListResponse) {
        pokemonListResponse.let { networkResult ->
            when (networkResult) {
                is NetworkStatus.Loading -> {
                    pokemonViewModel.isLoading.value = true
                }

                is NetworkStatus.Success -> {
                    pokemonViewModel.isLoading.value = false
                }

                is NetworkStatus.Failure -> {
                    pokemonViewModel.isLoading.value = true
                }

                else -> {}
            }
        }
    }

    //UI Part is Here------->>>
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                onClickNavigate = {
                },
                headingName = "POKEMON"
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    // Wrap the progress bar in a Box to center it
                    if (pokemonViewModel.isLoading.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomProgressBar()
                        }
                    }
                }
                itemsIndexed(pokemonViewModel.pokemonList) { index, data ->
                    if (data != null) {
                        PokemonItem(
                            pokemon = data,
                            index = index,
                            onCLickItem = {
                                pokemonViewModel.hasLoaded.value = false
                                val id = data.url?.let { it1 ->
                                    pokemonViewModel.extractLastValue(it1)
                                }

                                val bundle = Bundle()
                                if (id != null) {
                                    bundle.putString("id", id)
                                }
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    NavGraphRoute.DetailsScreen.route,
                                    bundle
                                )
                                navController.navigate(NavGraphRoute.DetailsScreen.route)
                            }
                        )
                    }
                }
                item {
                    if(pokemonViewModel.pokemonList.size >0){

                        Button(onClick = {
                            pokemonViewModel.callPokemonListApi(pokemonViewModel.offset, 20)
                            pokemonViewModel.offset += 20
                        }) {
                            TextBig("Load More", modifier = Modifier)
                        }
                    }
                }
            }

        }
    )
}

@Composable
fun PokemonItem(pokemon: Pokemon, index: Int, onCLickItem : () -> Unit) {
    val id = pokemon.url?.split("/")?.last { it.isNotEmpty() }
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color.Red),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onCLickItem() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextBig(
                    msg = "${index + 1}. ${pokemon.name!!.capitalize()}",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = rememberImagePainter(data = imageUrl),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(240.dp)
                )
            }
        }
    }

}

