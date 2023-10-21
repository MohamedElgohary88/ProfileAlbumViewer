package com.example.profilealbumviewer.viewmodels.user

import com.example.profilealbumviewer.model.AlbumDto
import com.example.profilealbumviewer.model.UserDto
import com.example.profilealbumviewer.viewmodels.base.BaseUIState

data class UserUiState(
    val userId: Int = (1..10).random(),
    val userName: String = "",
    val city: String = "",
    val street: String = "",
    val suite: String = "",
    val albums: List<AlbumUiState> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String? = null,
) : BaseUIState

fun UserDto.toUserUiState(): UserUiState {
    return UserUiState(
        userId = id,
        userName = name,
        city = address.city,
        street = address.street,
        suite = address.suite
    )
}

data class AlbumUiState(
    val id: Int,
    val title: String
)

fun AlbumDto.toAlbumUiState(): AlbumUiState {
    return AlbumUiState(
        id = id,
        title = title
    )
}

fun UserUiState.contentScreen() = !this.isError && this.albums.isNotEmpty()