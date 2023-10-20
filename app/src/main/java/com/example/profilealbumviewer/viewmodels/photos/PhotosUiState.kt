package com.example.profilealbumviewer.viewmodels.photos

import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.viewmodels.base.BaseUIState

data class PhotosUiState(
    val albumId: Int = 1,
    val photos: List<PhotoDetails> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String? = null,
) : BaseUIState

data class PhotoDetails(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String
)

fun PhotoDto.toPhotoDetails(): PhotoDetails {
    return PhotoDetails(
        albumId = albumId,
        id = id,
        title = title,
        url = url
    )
}