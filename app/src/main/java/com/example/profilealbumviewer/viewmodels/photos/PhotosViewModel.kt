package com.example.profilealbumviewer.viewmodels.photos

import com.example.profilealbumviewer.data.repository.UserDataRepository
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.viewmodels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val repository: UserDataRepository
) : BaseViewModel<PhotosUiState, PhotosUiEffect>(PhotosUiState()), PhotosInteractionListener {

    init {
        getAllPhotos()
    }

    override fun getAllPhotos() {
        _state.update {
            it.copy(isLoading = true, isError = false, error = null)
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