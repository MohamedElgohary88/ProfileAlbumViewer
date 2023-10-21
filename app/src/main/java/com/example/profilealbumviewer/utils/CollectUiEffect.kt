package com.example.profilealbumviewer.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.profilealbumviewer.viewmodels.base.BaseUiEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CollectUiEffect(
    effect: SharedFlow<BaseUiEffect>,
    effectHandler: (effect: BaseUiEffect) -> Unit
) {
    val throttledEffect = effect.throttleFirst(1000)
    LaunchedEffect(key1 = Unit) {
        throttledEffect.collectLatest { effect ->
            effectHandler(effect)
        }
    }
}

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    if (periodMillis < 0) return this
    return flow {
        conflate().collect { value ->
            emit(value)
            delay(periodMillis)
        }
    }
}