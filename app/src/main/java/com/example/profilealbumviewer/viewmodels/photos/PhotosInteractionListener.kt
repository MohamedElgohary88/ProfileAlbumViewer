package com.example.profilealbumviewer.viewmodels.photos

interface PhotosInteractionListener {
    fun getAllPhotos()
    fun onClickPhoto(photoId: Int)
}