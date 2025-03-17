package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelSeries(

    @Json(name = "available") val available: Int?  = null,
    @Json(name = "returned") val returned: Int? = null ,
    @Json(name = "collectionURI") val collectionURI: String? = null ,
    @Json(name = "items") val items: List<MarvelSeriesSummary>? = null
): Parcelable
