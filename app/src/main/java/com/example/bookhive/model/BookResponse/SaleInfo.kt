package com.example.bookhive.model.BookResponse

import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("allowAnonLogging")
    val allowAnonLogging: Boolean,

    @SerializedName("buyLink")
    val buyLink: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("isEbook")
    val isEbook: Boolean,

    @SerializedName("listPrice")
    val listPrice: ListPrice,

    @SerializedName("offers")
    val offers: List<Offer>,

    @SerializedName("retailPrice")
    val retailPrice: RetailPriceX,

    @SerializedName("saleability")
    val saleability: String
)