package com.santos.marvel.api

import com.santos.marvel.data.MarvelModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun getCharacters(@Query("ts") ts: String,
                              @Query("apikey") apiKey: String,
                              @Query("limit") limit: Int = 200,
                              @Query("hash") hash: String): MarvelModel
}