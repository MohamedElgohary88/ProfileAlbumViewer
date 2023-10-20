package com.example.profilealbumviewer.viewmodels.photos

interface PhotosInteractionListener {
    fun getAllPhotos()
    fun filterPhotos(query: String)
    fun onClickPhoto(photoId: Int)
}