package com.romero.init21musicplayer.viewmodel

import android.content.ContentResolver
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romero.init21musicplayer.models.RequestCall
import com.romero.init21musicplayer.repository.SongsRepository

class SongsViewModel(): ViewModel() {

    private val songRepo = SongsRepository()

    @RequiresApi(Build.VERSION_CODES.Q)
    fun fetchAllSongs(c: ContentResolver): MutableLiveData<RequestCall> {

        return songRepo.fetchAllSongs(c)

    }
}