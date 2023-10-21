package com.example.profilealbumviewer.ui.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.profilealbumviewer.R
import com.example.profilealbumviewer.ui.screens.composable.ContentVisibility
import com.example.profilealbumviewer.ui.screens.composable.Loading
import com.example.profilealbumviewer.ui.screens.composable.NoInternet
import com.example.profilealbumviewer.ui.screens.photos.navigateToPhotosScreen
import com.example.profilealbumviewer.ui.theme.Background
import com.example.profilealbumviewer.ui.theme.Black60
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.ui.theme.Jakarta
import com.example.profilealbumviewer.ui.theme.Primary
import com.example.profilealbumviewer.ui.theme.Typography
import com.example.profilealbumviewer.utils.CollectUiEffect
import com.example.profilealbumviewer.viewmodels.user.UserInteractionListener
import com.example.profilealbumviewer.viewmodels.user.UserUiEffect
import com.example.profilealbumviewer.viewmodels.user.UserUiState
import com.example.profilealbumviewer.viewmodels.user.UserViewModel
import com.example.profilealbumviewer.viewmodels.user.contentScreen

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
    NoInternet(state = state.isError, listener::getData)
    Loading(state = state.isLoading && state.albums.isEmpty())
    ContentVisibility(state = state.contentScreen()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens().SpacingXLarge)
                .background(color = Background)
        ) {
            Text(
                text = state.userName,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                color = Primary,
                textAlign = TextAlign.Center,
                style = Typography.titleLarge
            )
            Text(
                text = stringResource(R.string.address),
                style = Typography.bodyLarge,
                color = Primary,
                modifier = Modifier.padding(vertical = Dimens().SpacingXMedium)
            )
            Row {
                Text(
                    text = stringResource(R.string.city, state.city),
                    modifier = Modifier.padding(end = Dimens().SpacingXMedium),
                    color = Black60,
                    style = Typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.street, state.street),
                    color = Black60,
                    style = Typography.bodyMedium
                )
            }
            Text(
                text = stringResource(R.string.suite, state.suite),
                color = Black60,
                style = Typography.bodyMedium
            )

            Text(
                text = stringResource(R.string.my_albums),
                style = Typography.bodyLarge,
                color = Primary,
                modifier = Modifier.padding(
                    top = Dimens().SpacingXLarge,
                    bottom = Dimens().SpacingXMedium
                )
            )

            LazyColumn(contentPadding = PaddingValues(vertical = Dimens().SpacingXMedium)) {
                items(state.albums.size) { index ->
                    val album = state.albums[index]
                    AlbumItem(listener::onClickAlbum, album)
                }
            }
        }
    }
}