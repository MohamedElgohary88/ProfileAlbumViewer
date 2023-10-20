package com.example.profilealbumviewer.ui.screens.photos

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.profilealbumviewer.ui.screens.Screen

fun NavGraphBuilder.photosRoute() {

}

fun NavController.navigateToPhotosScreen(id: Int) {
    navigate("${Screen.Photos.route}/$id")
}

class AlbumArgs(savedStateHandle: SavedStateHandle) {
    val albumId: String = checkNotNull(savedStateHandle[ALBUM_ID])

    companion object {
        const val ALBUM_ID = "albumId"
    }
}