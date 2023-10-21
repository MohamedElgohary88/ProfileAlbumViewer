package com.example.profilealbumviewer.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.profilealbumviewer.ui.theme.Black60
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.ui.theme.OnSecondary
import com.example.profilealbumviewer.ui.theme.Typography
import com.example.profilealbumviewer.viewmodels.user.AlbumUiState

@Composable
fun AlbumItem(
    onClickItem: (Int) -> Unit,
    albumUiState: AlbumUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens().SpacingXMedium)
            .clickable { onClickItem(albumUiState.id) }
    ) {
        Text(
            text = albumUiState.title,
            color = Black60,
            style = Typography.bodyMedium
        )
        Divider(
            color = OnSecondary,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = Dimens().SpacingXMedium)
        )
    }
}