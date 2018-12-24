package com.pixelart.lastfmchallengemvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val name: String,
    val artist: String,
    val url: String,
    val image: List<Image>,
    val streamable: String,
    val mbid: String
):Parcelable