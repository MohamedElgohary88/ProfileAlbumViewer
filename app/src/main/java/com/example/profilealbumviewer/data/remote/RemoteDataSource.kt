package com.example.profilealbumviewer.data.remote

import com.example.profilealbumviewer.model.AlbumDto
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.model.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {
    @GET("users/{userId}")
    suspend fun getUserData(@Path("userId") userId: Int): Response<UserDto>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(@Path("userId") userId: Int): Response<List<AlbumDto>>

    @GET("albums/{albumId}/photos")
    suspend fun getAlbumPhotos(@Path("albumId") albumId: Int): Response<List<PhotoDto>>

    @GET("photos/{photoId}")
    suspend fun getPhoto(@Path("photoId") photoId: Int): Response<PhotoDto>

}