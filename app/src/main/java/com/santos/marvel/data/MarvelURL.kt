package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelURL (

    @Json(name = "type") val type: String? = null ,
    @Json(name = "url") val url: String? = null
): Parcelable

