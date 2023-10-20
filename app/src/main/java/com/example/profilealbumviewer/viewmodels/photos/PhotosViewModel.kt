package com.example.profilealbumviewer.viewmodels.photos

import androidx.lifecycle.SavedStateHandle
import com.example.profilealbumviewer.data.repository.UserDataRepository
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.ui.screens.photos.AlbumArgs
import com.example.profilealbumviewer.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: UserDataRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<PhotosUiState, PhotosUiEffect>(PhotosUiState()), PhotosInteractionListener {

    private val albumArgs: AlbumArgs = AlbumArgs(savedStateHandle)


    init {
        getAllPhotos()
    }

    override fun getAllPhotos() {
        _state.update {
            it.copy(
                albumId = albumArgs.albumId.toInt(),
                isLoading = true,
                isError = false,
                error = null
            )
        }
        tryToExecute(
            { repository.getAlbumPhotos(_state.value.albumId) },
            ::getAllPhotosSuccess,
            ::onError
        )
    }

    private fun getAllPhotosSuccess(photos: List<PhotoDto>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                photos = photos.map { photo -> photo.toPhotoDetails() }
            )
        }
    }

    override fun onClickPhoto(photoId: Int) {
        sendEffect(PhotosUiEffect.NavigateToPhotoViewer(photoId))
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message, isError = true) }
    }

}