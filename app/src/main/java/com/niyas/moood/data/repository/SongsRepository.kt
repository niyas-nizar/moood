package com.niyas.moood.data.repository

import com.niyas.moood.data.GetSongsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongsRepository @Inject constructor(private val getSongsHelper: GetSongsHelper) {

    suspend fun getAvailableSongs() = withContext(Dispatchers.IO) {
        getSongsHelper.getSongData()
    }
}