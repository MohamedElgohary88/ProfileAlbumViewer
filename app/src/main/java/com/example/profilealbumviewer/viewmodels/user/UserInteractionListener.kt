package com.example.profilealbumviewer.viewmodels.user

interface UserInteractionListener {
    fun getData()
    fun getUserData()
    fun getUserAlbums()
    fun onClickAlbum(albumId: Int)
}