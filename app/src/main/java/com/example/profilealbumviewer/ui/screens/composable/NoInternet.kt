package com.example.profilealbumviewer.ui.screens.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.profilealbumviewer.R
import com.example.profilealbumviewer.ui.theme.Dimens

@Composable
fun NoInternet(
    state: Boolean,
    onClickTryAgain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.no_internet)
    )
    AnimatedVisibility(
        visible = state,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(Dimens().SpacingXXLarge)) {
            Box(
                modifier = modifier
                    .fillMaxHeight(.8f)
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            }
            Button(onClick = onClickTryAgain, modifier = Modifier.fillMaxWidth()) {
                Text(text = "try again!")
            }
        }

    }
}