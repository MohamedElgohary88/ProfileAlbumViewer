package com.example.profilealbumviewer.viewmodels.viewer

import androidx.lifecycle.SavedStateHandle
import com.example.profilealbumviewer.data.repository.UserDataRepository
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.ui.screens.photo_viewer.PhotoArgs
import com.example.profilealbumviewer.viewmodels.base.BaseViewModel
import com.example.profilealbumviewer.viewmodels.photos.PhotoDetails
import com.example.profilealbumviewer.viewmodels.photos.toPhotoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PhotoViewerViewModel @Inject constructor(
    private val repository: UserDataRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ViewerUiState, ViewerUiEffect>(ViewerUiState()), ViewerInteractionListener {

    private val photoArgs: PhotoArgs = PhotoArgs(savedStateHandle)

    init {
        getPhoto(photoArgs.photoId.toInt())
    }


    override fun getPhoto(photoId: Int) {
        tryToExecute(
            { repository.getPhoto(photoId) },
            ::onGetPhotoSuccess,
            ::onError
        )

    }

    private fun onGetPhotoSuccess(photoDto: PhotoDto) {
        val photo = photoDto.toPhotoDetails()
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                id = photo.id,
                url = photo.url,
                title = photo.title
            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message, isError = true) }
    }

}