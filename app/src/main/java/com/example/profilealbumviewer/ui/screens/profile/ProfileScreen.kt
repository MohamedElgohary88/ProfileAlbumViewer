package com.example.profilealbumviewer.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.profilealbumviewer.ui.screens.photos.navigateToPhotosScreen
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.utils.CollectUiEffect
import com.example.profilealbumviewer.viewmodels.user.UserInteractionListener
import com.example.profilealbumviewer.viewmodels.user.UserUiEffect
import com.example.profilealbumviewer.viewmodels.user.UserUiState
import com.example.profilealbumviewer.viewmodels.user.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            is UserUiEffect.NavigateToPhotosScreen -> {
                navController.navigateToPhotosScreen(effect.albumId)
            }
        }
    }

    ProfileContent(state = state, listener = viewModel)
}

@Composable
fun ProfileContent(
    state: UserUiState,
    listener: UserInteractionListener
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = state.userName,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Address",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(text = "City: ${state.city}")
        Text(text = "Street: ${state.street}")
        Text(text = "Suite: ${state.suite}")

        Text(
            text = "My Albums",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = Dimens().SpacingXLarge)
        )

        LazyColumn {
            items(state.albums.size) { index ->
                val album = state.albums[index]
                AlbumItem(listener::onClickAlbum, album)
            }
        }
    }
}