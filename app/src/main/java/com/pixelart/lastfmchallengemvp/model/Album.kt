package com.pixelart.lastfmchallengemvp.model

data class Album(
    val name: String,
    val artist: String,
    val url: String,
    val image: List<Image>,
    val streamable: String,
    val mbid: String
)