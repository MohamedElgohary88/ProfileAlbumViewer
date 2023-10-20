package com.example.profilealbumviewer.viewmodels.user

import com.example.profilealbumviewer.data.repository.UserDataRepository
import com.example.profilealbumviewer.model.AlbumDto
import com.example.profilealbumviewer.model.UserDto
import com.example.profilealbumviewer.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserDataRepository
) : BaseViewModel<UserUiState, UserUiEffect>(UserUiState()), UserInteractionListener {

    init {
        getData()
    }

    override fun getData() {
        getUserData()
        getUserAlbums()
    }

    override fun getUserData() {
        _state.update {
            it.copy(isLoading = true, isError = false, error = null)
        }
        tryToExecute(
            { repository.getUserData(_state.value.userId) },
            ::getUserDataSuccess,
            ::onError
        )
    }

    private fun getUserDataSuccess(userDto: UserDto) {
        val user = userDto.toUserUiState()
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                userName = user.userName,
                city = user.city,
                street = user.street,
                suite = user.suite
            )
        }
    }

    override fun getUserAlbums() {
        _state.update {
            it.copy(isLoading = true, isError = false, error = null)
        }
        tryToExecute(
            { repository.getUserAlbums(_state.value.userId) },
            ::getAllAlbumsSuccess,
            ::onError
        )
    }

    private fun getAllAlbumsSuccess(albums: List<AlbumDto>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                albums = albums.map { album -> album.toAlbumUiState() }
            )
        }
    }

    override fun onClickAlbum(albumId: Int) {
        sendEffect(UserUiEffect.NavigateToPhotosScreen(albumId))
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message, isError = true) }
    }

}