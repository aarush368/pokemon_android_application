package com.example.pokemon.view.graph

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemon.view.ui.screen.DetailsScreen
import com.example.pokemon.view.ui.screen.PokemonListScreen
import com.example.pokemon.viewModel.PokemonViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraphs(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavGraphRoute.PokemonListScreen.route
    ) {
        composable(NavGraphRoute.PokemonListScreen.route) { backStackEntry ->
            val pokemonViewModel: PokemonViewModel = hiltViewModel(backStackEntry)
            PokemonListScreen(
                navController = navHostController,
                pokemonViewModel = pokemonViewModel
            )
        }
        composable(NavGraphRoute.DetailsScreen.route) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navHostController.getBackStackEntry(NavGraphRoute.PokemonListScreen.route) }
            val pokemonViewModel: PokemonViewModel = hiltViewModel(parentEntry)
            val bundle =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Bundle>(
                    NavGraphRoute.DetailsScreen.route
                )
                    ?: bundleOf()
            DetailsScreen(navHostController, bundle = bundle, pokemonViewModel = pokemonViewModel)

        }
    }
}

sealed class NavGraphRoute(val route: String) {
    object PokemonListScreen : NavGraphRoute("pokemon_list_screen")
    object DetailsScreen : NavGraphRoute("details_screen")
}
