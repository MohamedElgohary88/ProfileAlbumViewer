package com.example.profilealbumviewer

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.profilealbumviewer.ui.screens.Screen
import com.example.profilealbumviewer.ui.screens.photos.AlbumArgs
import com.example.profilealbumviewer.ui.screens.photos.PhotosScreen
import com.example.profilealbumviewer.ui.screens.photos.photosRoute
import com.example.profilealbumviewer.ui.screens.profile.ProfileScreen

@Composable
fun ProfileAlbumNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Profile.route) {
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(route = "${Screen.Photos.route}/{${AlbumArgs.ALBUM_ID}}",
            arguments = listOf(navArgument(AlbumArgs.ALBUM_ID) { NavType.IntType })
        ) {
            PhotosScreen(navController)
        }
    }
}