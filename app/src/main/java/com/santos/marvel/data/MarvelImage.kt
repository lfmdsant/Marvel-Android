package com.santos.marvel.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

data class MarvelImage(
    @Json(name = "path") val path: String? = null ,
    @Json(name = "extension") val extension: String? = null
): Parcelable {
    val thumbnailURL: String
        get() = if (!path.isNullOrBlank() && !extension.isNullOrBlank()) {
            "$path.$extension".replace("http://", "https://")
        } else {
            ""
        }
}
