package com.example.bookhive.model.BookDetail

import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("extraLarge")
    val extraLarge: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("smallThumbnail")
    val smallThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)