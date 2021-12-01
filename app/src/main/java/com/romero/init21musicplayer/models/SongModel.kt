package com.romero.init21musicplayer.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SongModel(
    val titleSong: String,
    val durationSong: String,
    val pathSong: String,
    val artUri: String
): Parcelable