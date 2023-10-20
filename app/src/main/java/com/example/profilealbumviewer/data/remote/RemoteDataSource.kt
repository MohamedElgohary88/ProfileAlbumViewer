package com.example.profilealbumviewer.data.remote

import com.example.profilealbumviewer.model.AlbumResponse
import com.example.profilealbumviewer.model.PhotoResponse
import com.example.profilealbumviewer.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {
    @GET("users/{userId}")
    suspend fun getUserData(@Path("userId") userId: Int): Response<UserResponse>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(@Path("userId") userId: Int): Response<List<AlbumResponse>>

    @GET("albums/{albumId}/photos")
    suspend fun getAlbumPhotos(@Path("albumId") albumId: Int): Response<List<PhotoResponse>>
}