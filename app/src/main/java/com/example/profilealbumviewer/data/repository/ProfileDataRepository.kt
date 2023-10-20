package com.example.profilealbumviewer.data.repository

import com.example.profilealbumviewer.model.AlbumResponse
import com.example.profilealbumviewer.model.PhotoResponse
import com.example.profilealbumviewer.model.UserResponse

interface ProfileDataRepository {
    suspend fun getUserData(userId: Int): UserResponse

    suspend fun getUserAlbums(userId: Int): List<AlbumResponse>

    suspend fun getAlbumPhotos(albumId: Int): List<PhotoResponse>
}