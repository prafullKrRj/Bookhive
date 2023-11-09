package com.example.bookhive.model.BookResponse

import com.google.gson.annotations.SerializedName

data class ImageLinks(
   /* @SerializedName("smallThumbnail")
    val smallThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String*/

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