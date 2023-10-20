package com.example.profilealbumviewer.ui.screens

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Photos : Screen("photos")
}
