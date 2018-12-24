package com.pixelart.lastfmchallengemvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
):Parcelable