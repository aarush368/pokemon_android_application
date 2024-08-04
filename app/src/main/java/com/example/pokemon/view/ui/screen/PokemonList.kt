package com.example.pokemon.view.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemon.utility.NetworkStatus
import com.example.pokemon.view.graph.NavGraphRoute
import com.example.pokemon.model.Pokemon
import com.example.pokemon.view.ui.customUi.CustomProgressBar
import com.example.pokemon.view.ui.customUi.TextBig
import com.example.pokemon.view.ui.customUi.TextBold
import com.example.pokemon.view.ui.customUi.Toolbar
import com.example.pokemon.view.ui.theme.RobotoMedium
import com.example.pokemon.viewModel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PokemonListScreen(
    pokemonViewModel: PokemonViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = pokemonViewModel.hasLoaded.value) {
        if (pokemonViewModel.hasLoaded.value) {
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.material.MaterialTheme.colors.onPrimary),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextBold(
                            msg = "Pokemon",
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(modifier = Modifier.width(36.dp))
                    }
                },
                elevation = 10.dp
            )
        },
        content = {
            PokemonGrid(pokemonViewModel = pokemonViewModel, navController = navController)
        }
    )
}

@Composable
fun PokemonGrid(pokemonViewModel: PokemonViewModel, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        if (pokemonViewModel.isLoading.value) {
            item(span = { GridItemSpan(maxLineSpan) }) {
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
                val id = data.url?.split("/")?.last { it.isNotEmpty() }
                val imageUrl =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                PokemonItem(
                    pokemon = data,
                    imageUrl = imageUrl,
                    onClickItem = {
                        pokemonViewModel.hasLoaded.value = false
                        val id = data.url?.let { it1 ->
                            pokemonViewModel.extractLastValue(it1)
                        }

                        val bundle = Bundle()
                        if (id != null) {
                            bundle.putString("id", id)
                            bundle.putString("imageUrl", imageUrl)
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

        if (pokemonViewModel.pokemonList.isNotEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Button(
                    onClick = {
                        pokemonViewModel.callPokemonListApi(pokemonViewModel.offset, 20)
                        pokemonViewModel.offset += 20
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Load More")
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClickItem: () -> Unit, imageUrl: String) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.5f)
            .height(220.dp)
            .clickable { onClickItem() }
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(data = imageUrl),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = pokemon.name?.replaceFirstChar { it.uppercase() }.orEmpty(),
                    fontFamily = RobotoMedium,
                    fontSize = 24.sp
                )
            }
        }
    }
}