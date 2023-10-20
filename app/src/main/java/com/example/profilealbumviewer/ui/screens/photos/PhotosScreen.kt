package com.example.profilealbumviewer.ui.screens.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.profilealbumviewer.R
import com.example.profilealbumviewer.ui.screens.composable.CommonTextField
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.ui.theme.LightOrange
import com.example.profilealbumviewer.utils.CollectUiEffect
import com.example.profilealbumviewer.viewmodels.photos.PhotoDetails
import com.example.profilealbumviewer.viewmodels.photos.PhotosInteractionListener
import com.example.profilealbumviewer.viewmodels.photos.PhotosUiState
import com.example.profilealbumviewer.viewmodels.photos.PhotosViewModel

@Composable
fun PhotosScreen(
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {

        }
    }

    PhotosContent(
        state = state,
        listener = viewModel
    )
}

@Composable
fun PhotosContent(
    state: PhotosUiState,
    listener: PhotosInteractionListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = Dimens().SpacingXLarge,
                    end = Dimens().SpacingXLarge,
                    bottom = Dimens().SpacingXMedium
                )
                .border(1.dp, LightOrange, shape = RoundedCornerShape(Dimens().Radius8))
        ) {
            CommonTextField(value = "", onValueChange = {}, hint = "Search", leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.magnifer),
                    contentDescription = null
                )
            })
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(state.photos.size) { index ->
                val photo = state.photos[index]
                PhotoItem(listener::onClickPhoto, photo)
            }
        }
    }
}

@Composable
fun PhotoItem(
    onClickItem: (Int) -> Unit,
    photo: PhotoDetails
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(
                1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClickItem(photo.id) }
    ) {
        AsyncImage(
            modifier = Modifier.wrapContentSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}