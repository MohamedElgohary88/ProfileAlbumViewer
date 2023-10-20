package com.example.profilealbumviewer.data.repository

import com.example.profilealbumviewer.data.remote.RemoteDataSource
import com.example.profilealbumviewer.model.AlbumResponse
import com.example.profilealbumviewer.model.PhotoResponse
import com.example.profilealbumviewer.model.UserResponse
import com.example.profilealbumviewer.utils.NoInternetException
import com.example.profilealbumviewer.utils.NotFoundException
import com.example.profilealbumviewer.utils.NullResultException
import com.example.profilealbumviewer.utils.ServerException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class UserDataRepositoryImplementation(private val dataSource: RemoteDataSource) :
    UserDataRepository {
    override suspend fun getUserData(userId: Int): UserResponse {
        return wrapApiCall { dataSource.getUserData(userId) }
    }

    override suspend fun getUserAlbums(userId: Int): List<AlbumResponse> {
        return wrapApiCall { dataSource.getUserAlbums(userId) }
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<PhotoResponse> {
        return wrapApiCall { dataSource.getAlbumPhotos(albumId) }
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