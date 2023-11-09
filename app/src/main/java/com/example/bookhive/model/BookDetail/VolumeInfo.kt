package com.example.bookhive.model.BookDetail

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    val allowAnonLogging: Boolean,
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("canonical_volume_link")
    val canonicalVolumeLink: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("content_version")
    val contentVersion: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("dimensions")
    val dimensions: Dimensions,
    @SerializedName("image_links")
    val imageLinks: ImageLinks,
    @SerializedName("industry_identifiers")
    val industryIdentifiers: List<IndustryIdentifier>,
    @SerializedName("info_link")
    val infoLink: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("maturity_rating")
    val maturityRating: String,
    @SerializedName("page_count")
    val pageCount: Int,
    @SerializedName("panelization_summary")
    val panelizationSummary: PanelizationSummary,
    @SerializedName("preview_link")
    val previewLink: String,
    @SerializedName("print_type")
    val printType: String,
    @SerializedName("printed_page_count")
    val printedPageCount: Int,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("reading_modes")
    val readingModes: ReadingModes,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)