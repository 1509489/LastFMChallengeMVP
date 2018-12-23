package com.pixelart.lastfmchallengemvp.common

const val BASE_URL = "https://ws.audioscrobbler.com/"
const val END_POINT = "2.0/"
const val METHOD = "album.search"
const val API_KEY = "3669a7c4f521d2a8ebe17b8426defba0"
const val FORMAT = "json"
var PAGE: Int = 1
const val LIMIT: Int = 30


val CONNECT_TIMEOUT: Long = 30 //seconds
val READ_TIMEOUT: Long = 30