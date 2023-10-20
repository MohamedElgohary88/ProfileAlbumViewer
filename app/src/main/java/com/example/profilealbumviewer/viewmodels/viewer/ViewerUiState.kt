package com.example.profilealbumviewer.viewmodels.viewer

import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.viewmodels.base.BaseUIState
import com.example.profilealbumviewer.viewmodels.photos.PhotoDetails

data class ViewerUiState(
    val id: Int = 0,
    val title: String = "",
    val url: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String? = null,
) : BaseUIState

fun PhotoDto.toViewerUiState(): ViewerUiState {
    return ViewerUiState(
        id = id,
        title = title,
        url = url
    )
}