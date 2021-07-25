package com.example.covid19app.models


import com.google.gson.annotations.SerializedName

data class Cases(
    @SerializedName("3/21/20")
    var x32120: Int?,
    @SerializedName("3/22/20")
    var x32220: Int?,
    @SerializedName("3/23/20")
    var x32320: Int?,
    @SerializedName("3/24/20")
    var x32420: Int?,
    @SerializedName("3/25/20")
    var x32520: Int?,
    @SerializedName("3/26/20")
    var x32620: Int?,
    @SerializedName("3/27/20")
    var x32720: Int?,
    @SerializedName("3/28/20")
    var x32820: Int?,
    @SerializedName("3/29/20")
    var x32920: Int?,
    @SerializedName("3/30/20")
    var x33020: Int?
)