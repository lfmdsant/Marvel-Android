package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelData(

    @Json(name = "offset") val offset: Int? = null,
    @Json(name = "limit") val limit: Int? = null ,
    @Json(name = "total") val total: Int?  = null,
    @Json(name = "count") val count: Int? = null ,
    @Json(name = "results") val results: List<MarvelResults>? = null

): Parcelable
