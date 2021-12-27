package com.romero.init21musicplayer.repository

import android.content.ContentResolver
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.romero.init21musicplayer.models.AlbumModel
import com.romero.init21musicplayer.models.RequestCall
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.utils.Constants
import com.romero.init21musicplayer.utils.Utils
import java.util.ArrayList

class SongsRepository {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun fetchAllSongs(c: ContentResolver): MutableLiveData<RequestCall> {

        val songs = ArrayList<SongModel>()

        val r = RequestCall()
        r.status = Constants.LOADING
        r.songs = songs

        val mLiveData = MutableLiveData<RequestCall>()
        mLiveData.value = r

        val cursor = Utils.querySongs(c)

        if(cursor != null && cursor.moveToFirst()) {

            do {

                songs.add(Utils.getSong(cursor))

            } while (cursor.moveToNext())

            cursor.close()

        }

        r.status = Constants.STOPPED
        r.songs = songs

        mLiveData.postValue(r)

        return mLiveData

    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun fetchAllAlbums(c: ContentResolver): MutableLiveData<ArrayList<AlbumModel>> {

        val albums = ArrayList<AlbumModel>()

        val mLiveData = MutableLiveData<ArrayList<AlbumModel>>()

        val cursor = Utils.queryAlbums(c)

        if(cursor != null && cursor.moveToFirst()) {

            do {

                val newAlbum: AlbumModel = Utils.getAlbum(cursor)

                if(!albums.any { it -> it.name == newAlbum.name }) {
                    albums.add(newAlbum)
                }

            } while (cursor.moveToNext())

            cursor.close()

        }

        mLiveData.postValue(albums)

        return mLiveData

    }

}