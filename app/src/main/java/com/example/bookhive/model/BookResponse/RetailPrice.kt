package com.example.bookhive.model.BookResponse

import com.google.gson.annotations.SerializedName

data class RetailPrice(
    @SerializedName("amountInMicros")
    val amountInMicros: Double,

    @SerializedName("currencyCode")
    val currencyCode: String
)