package com.santos.marvel.model

import com.santos.marvel.api.ApiService
import com.santos.marvel.data.MarvelModel


class CharacterRepository (private val apiService: ApiService) {

    suspend fun fetchCharacters(ts: String,
                                apikey: String,
                                hash: String)
                                : MarvelModel {
       return apiService.getCharacters(ts = ts,
                                        apiKey = apikey,
                                        hash = hash,
                                        limit = 100)

    }
}
