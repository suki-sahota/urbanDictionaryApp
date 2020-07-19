package com.example.urbandictionaryapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class SearchModel {
    @Json(name = "list") var list: List<DefinitionModel> = emptyList()
}

@Parcelize
data class DefinitionModel(
    @Json(name = "definition") val definition: String,
    @Json(name = "thumbs_up") val thumbs_up: Int,
    @Json(name = "thumbs_down") val thumbs_down: Int
): Parcelable