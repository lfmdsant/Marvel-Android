package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelResults(

    @Json(name = "id") val id: Int? = null ,
    @Json(name = "name") val name: String?  = null,
    @Json(name = "description") val description: String? = null ,
    @Json(name = "modified") val modified: String? = null ,
    @Json(name = "resourceURI") val resourceURI: String? = null ,
    @Json(name = "urls") val urls: List<MarvelURL>? = null ,
    @Json(name = "thumbnail") val thumbnail: MarvelImage? = null ,
    @Json(name = "comics") val comics: MarvelComics? = null ,
    @Json(name = "stories") val stories: MarvelStores? = null ,
    @Json(name = "events") val events: MarvelEvents? = null ,
    @Json(name = "series") val series: MarvelSeries? = null,
    @Json(name = "isFavorite")var isFavorite: Boolean = false
): Parcelable


