package com.example.pokemon.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pokemon.utility.NetworkStatus
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonDetailsResponse
import com.example.pokemon.model.PokemonListResponse
import com.example.pokemon.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    val isLoading = mutableStateOf(true)
    val scope = CoroutineScope(Dispatchers.IO)
    private val _pokemonListResponse = MutableStateFlow<NetworkStatus<PokemonListResponse?>?>(null)
    val pokemonListResponse: StateFlow<NetworkStatus<PokemonListResponse?>?> = _pokemonListResponse
    val pokemonList = mutableStateListOf<Pokemon?>()
    var count = ""
    var nextPokemon = ""
    var previousPokemon = ""
    var hasLoaded = mutableStateOf(true)
    var offset = 20


    fun callPokemonListApi(offset : Int,limit : Int) {
        scope.launch {
            when (val result = apiRepository.getPokemonList(offset, limit)) {
                is NetworkStatus.Loading -> {
                    isLoading.value = true
                }

                is NetworkStatus.Success -> {
                    if (result.value.isSuccessful) {
                        isLoading.value = false
                        count = result.value.body()?.count.toString()
                        previousPokemon = result.value.body()?.previous.toString()
                        nextPokemon = result.value.body()?.next.toString()
                        result.value.body()?.results?.let { list ->
                            pokemonList.addAll(list)
                        }
                        _pokemonListResponse.emit(NetworkStatus.Success(result.value.body()))
                    }
                }

                is NetworkStatus.Failure -> {
                    isLoading.value = false
                    NetworkStatus.Failure(
                        result.isNetworkError,
                        result.errorCode,
                        result.errorBody
                    )
                }

            }
        }
    }

    private val _pokemonDetailsResponse = MutableStateFlow<NetworkStatus<PokemonDetailsResponse?>?>(null)
    val pokemonDetailsResponse : StateFlow<NetworkStatus<PokemonDetailsResponse?>?> = _pokemonDetailsResponse
    var pokemonDetailsData : PokemonDetailsResponse? = null

    fun callPokemonDetailsApi(id : String){
        scope.launch {
            when(val result = apiRepository.getPokemonDetails(id)){
                NetworkStatus.Loading -> {
                    isLoading.value = true
                }
                is NetworkStatus.Success -> {
                   isLoading.value = false
                    if (result.value.isSuccessful){
                        pokemonDetailsData = result.value.body()
                        _pokemonDetailsResponse.emit(NetworkStatus.Success(result.value.body()))
                    }
                }
                is NetworkStatus.Failure -> {
                    isLoading.value = false
                    NetworkStatus.Failure(
                        result.isNetworkError,
                        result.errorCode,
                        result.errorBody
                    )
                }
            }
        }
    }

    fun extractLastValue(url: String): String {
        val parts = url.split("/")
        return parts.filter { it.isNotEmpty() }.last()
    }
}