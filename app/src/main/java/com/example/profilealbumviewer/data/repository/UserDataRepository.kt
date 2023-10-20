package com.example.profilealbumviewer.data.repository

import com.example.profilealbumviewer.model.AlbumDto
import com.example.profilealbumviewer.model.PhotoDto
import com.example.profilealbumviewer.model.UserDto

interface UserDataRepository {
    suspend fun getUserData(userId: Int): UserDto

    suspend fun getUserAlbums(userId: Int): List<AlbumDto>

    suspend fun getAlbumPhotos(albumId: Int): List<PhotoDto>
}