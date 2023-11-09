package com.example.bookhive.model

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Double,
    @SerializedName("listPrice")
    val listPrice: ListPriceX,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice
)
