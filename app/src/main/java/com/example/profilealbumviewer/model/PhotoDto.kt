package com.example.profilealbumviewer.model

data class PhotoDto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
