package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelComicSummary(

    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null

): Parcelable
