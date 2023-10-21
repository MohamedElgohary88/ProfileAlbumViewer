package com.example.profilealbumviewer.ui.screens.photo_viewer

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.profilealbumviewer.R
import com.example.profilealbumviewer.ui.screens.composable.ContentVisibility
import com.example.profilealbumviewer.ui.screens.composable.Loading
import com.example.profilealbumviewer.ui.screens.composable.NoInternet
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.ui.theme.Primary
import com.example.profilealbumviewer.viewmodels.viewer.PhotoViewerViewModel
import com.example.profilealbumviewer.viewmodels.viewer.ViewerInteractionListener
import com.example.profilealbumviewer.viewmodels.viewer.ViewerUiState
import com.example.profilealbumviewer.viewmodels.viewer.contentScreen
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun PhotoViewerScreen(
    viewModel: PhotoViewerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PhotoViewerContent(state, viewModel)
}

@Composable
fun PhotoViewerContent(state: ViewerUiState, listener: ViewerInteractionListener) {
    Loading(state = state.isLoading && state.url.isEmpty())
    NoInternet(state = state.isError, listener::getPhoto)
    ContentVisibility(state = state.contentScreen()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens().SpacingMedium)
                .border(
                    1.dp,
                    color = Primary,
                    shape = RoundedCornerShape(Dimens().Radius8)
                )
                .zoomable(rememberZoomState())
                .clip(RoundedCornerShape(Dimens().Radius8)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.no_img_2),
            placeholder = painterResource(R.drawable.no_img_2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}