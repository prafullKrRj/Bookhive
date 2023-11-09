package com.example.bookhive.model.BookResponse

import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("height")
    val height: String,
    @SerializedName("thickness")
    val thickness: String,
    @SerializedName("width")
    val width: String
)