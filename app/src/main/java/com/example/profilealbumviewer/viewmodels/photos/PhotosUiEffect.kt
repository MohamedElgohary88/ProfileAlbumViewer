package com.example.profilealbumviewer.viewmodels.photos

import com.example.profilealbumviewer.viewmodels.base.BaseUiEffect
import com.example.profilealbumviewer.viewmodels.user.UserUiEffect

sealed interface PhotosUiEffect : BaseUiEffect {
    data class NavigateToPhotoViewer(val photoId: Int) : PhotosUiEffect
}