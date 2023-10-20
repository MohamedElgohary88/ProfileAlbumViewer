package com.example.profilealbumviewer.ui.screens.photo_viewer

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.profilealbumviewer.ui.screens.Screen

fun NavController.navigateToPhotoViewer(id: Int) {
    navigate("${Screen.PhotoViewer.route}/$id")
}

class PhotoArgs(savedStateHandle: SavedStateHandle) {
    val photoId: String = checkNotNull(savedStateHandle[PHOTO_ID])

    companion object {
        const val PHOTO_ID = "photoId"
    }
}