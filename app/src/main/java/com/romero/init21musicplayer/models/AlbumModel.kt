package com.romero.init21musicplayer.models

import java.util.ArrayList

data class AlbumModel(
    val Id: String,
    val name: String,
    val artist: String,
    val artUri: String,
    val songs: ArrayList<SongModel>
)