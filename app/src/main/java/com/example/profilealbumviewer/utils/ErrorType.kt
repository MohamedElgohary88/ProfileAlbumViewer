package com.example.profilealbumviewer.utils

open class ProfileAlbumException(message: String?) : Exception(message)

class NullResultException(message: String?) : ProfileAlbumException(message)
class NotFoundException(message: String?) : ProfileAlbumException(message)
class NoInternetException(message: String?) : ProfileAlbumException(message)
class ServerException(message: String?) : ProfileAlbumException(message)