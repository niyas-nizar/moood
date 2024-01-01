package com.niyas.moood.data.songs

import android.net.Uri

data class Song(
    val uri: Uri,
    val id: Long,
    val displayName: String,
    val title: String,
    val data: String,
    val artist: String,
    val duration: String
)
