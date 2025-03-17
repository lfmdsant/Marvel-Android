package com.santos.marvel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.marvel.api.ApiCredential
import com.santos.marvel.api.ApiService
import com.santos.marvel.data.MarvelModel
import com.santos.marvel.data.MarvelResults
import com.santos.marvel.helper.Md5Generator
import com.santos.marvel.model.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
   single {
        Retrofit.Builder()
            .baseUrl(ApiCredential.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
    single { CharacterRepository(get()) }
    viewModel { HomeViewModel(get()) }
}
class HomeViewModel(private val repository: CharacterRepository): ViewModel() {

    private val _characters = MutableStateFlow<MarvelModel?>(null)
    val characters: StateFlow<MarvelModel?> get() = _characters

    private val _favorites = MutableStateFlow<List<MarvelResults>>(emptyList())
    val favorites: StateFlow<List<MarvelResults>> get() = _favorites

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val favoriteIds = mutableSetOf<Int>()

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val timestamp = System.currentTimeMillis().toString()
                val apikey = ApiCredential.publicKey
                val hash = Md5Generator.generateMd5Hash(
                    timestamp = timestamp,
                    publicKey = apikey,
                    privateKey = ApiCredential.privatekey
                )
                val result = withContext(Dispatchers.IO) {
                        withTimeout(100000L) {
                        repository.fetchCharacters(ts = timestamp, apikey = apikey, hash = hash)
                    }
                }
                Log.d("HomeViewModel", "Resultado da API: $result")
                _characters.value = result
            } catch (e: TimeoutCancellationException) {
                _error.value = "Tempo limite atingido. Por favor, tente novamente."
                Log.e("HomeViewModel", "Timeout na chamada da API", e)
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("HomeViewModel", "Erro ao buscar personagens", e)
            }
        }
    }

    fun toggleFavorite(character: MarvelResults) {
        if (favoriteIds.contains(character.id)) {
            favoriteIds.remove(character.id)
            character.isFavorite = false
        } else {
            character.id?.let { favoriteIds.add(it) }
            character.isFavorite = true
        }
        val currentCharacters = _characters.value?.data?.results.orEmpty()
        _favorites.value = currentCharacters.filter { favoriteIds.contains(it.id) }
        Log.d("HomeViewModel", "Favorites atualizados: ${_favorites.value.size} itens")
    }
}