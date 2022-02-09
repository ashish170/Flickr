package com.example.flickr.data

data class response (

    val photos: picsData
)

data class picsData(
    val page: Int,
    val photo: List<picsResponse>
)

data class picsResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)