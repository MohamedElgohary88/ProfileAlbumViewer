package com.example.profilealbumviewer.data.repository

import com.example.profilealbumviewer.data.remote.RemoteDataSource
import com.example.profilealbumviewer.model.AlbumDto
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.model.UserDto
import com.example.profilealbumviewer.utils.NoInternetException
import com.example.profilealbumviewer.utils.NotFoundException
import com.example.profilealbumviewer.utils.NullResultException
import com.example.profilealbumviewer.utils.ServerException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class UserDataRepositoryImplementation @Inject constructor(
    private val dataSource: RemoteDataSource
) : UserDataRepository {
    override suspend fun getUserData(userId: Int): UserDto {
        return wrapApiCall { dataSource.getUserData(userId) }
    }

    override suspend fun getUserAlbums(userId: Int): List<AlbumDto> {
        return wrapApiCall { dataSource.getUserAlbums(userId) }
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<PhotoDto> {
        return wrapApiCall { dataSource.getAlbumPhotos(albumId) }
    }

    override suspend fun getPhoto(photoId: Int): PhotoDto {
        return wrapApiCall { dataSource.getPhoto(photoId) }
    }

    private suspend fun <T> wrapApiCall(function: suspend () -> Response<T>): T {
        try {
            val response = function()
            if (response.isSuccessful) {
                return response.body() ?: throw NullResultException("No data")
            } else {
                throw when (response.code()) {
                    404 -> NotFoundException("Not found")
                    else -> ServerException("Server error")
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException("no Internet")
        } catch (io: IOException) {
            throw NoInternetException(io.message)
        }
    }
}