package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelModel(

    @Json(name = "code") val code: Int?  = null,
    @Json(name = "status") val status: String? = null ,
    @Json(name = "copyright") val copyright: String? = null ,
    @Json(name = "attributionText") val attributionText: String?  = null,
    @Json(name = "attributionHTML") val attributionHTML: String?  = null,
    @Json(name = "data") val data: MarvelData? = null
): Parcelable
