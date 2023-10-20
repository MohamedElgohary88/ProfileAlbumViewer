package com.example.profilealbumviewer.ui.screens.photo_viewer

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.profilealbumviewer.viewmodels.viewer.PhotoViewerViewModel
import com.example.profilealbumviewer.viewmodels.viewer.ViewerUiState
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun PhotoViewerScreen(
    viewModel: PhotoViewerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    PhotoViewerContent(state)

}

@Composable
fun PhotoViewerContent(photo: ViewerUiState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(
                1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .zoomable(rememberZoomState()),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}