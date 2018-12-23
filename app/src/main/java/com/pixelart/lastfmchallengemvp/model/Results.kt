package com.pixelart.lastfmchallengemvp.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery,

    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String,

    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String,

    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String,

    val albummatches: Albummatches,

    @SerializedName("@attr")
    val attr: Attr
)