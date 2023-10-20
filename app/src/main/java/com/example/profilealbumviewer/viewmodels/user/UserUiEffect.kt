package com.example.profilealbumviewer.viewmodels.user

import com.example.profilealbumviewer.viewmodels.base.BaseUiEffect

sealed interface UserUiEffect : BaseUiEffect {
    data class NavigateToPhotosScreen(val albumId: Int) : UserUiEffect
}