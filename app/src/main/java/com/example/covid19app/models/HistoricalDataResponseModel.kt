package com.example.covid19app.models


import com.google.gson.annotations.SerializedName

data class HistoricalDataResponseModel(
    @SerializedName("country")
    var country: String?,
    @SerializedName("provinces")
    var provinces: List<String>?,
    @SerializedName("timeline")
    var timeline: Timeline?
)