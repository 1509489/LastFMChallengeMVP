package com.pixelart.lastfmchallengemvp.model

import com.google.gson.annotations.SerializedName

data class OpensearchQuery(
    @SerializedName("#text")
    val text: String,
    val role: String,
    val searchTerms: String,
    val startPage: String
)