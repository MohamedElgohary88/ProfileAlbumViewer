package com.example.profilealbumviewer.ui.screens.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.profilealbumviewer.R
import com.example.profilealbumviewer.ui.theme.Black60
import com.example.profilealbumviewer.ui.theme.Dimens
import com.example.profilealbumviewer.ui.theme.Typography

@Composable
fun EmptyPlaceholder(
    state: Boolean,
    text: String = stringResource(R.string.the_search_result_is_empty)
) {
    AnimatedVisibility(
        visible = state,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 500)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.search_not_found),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                style = Typography.bodyLarge,
                color = Black60,
                textAlign = TextAlign.Center
            )
        }
    }
}