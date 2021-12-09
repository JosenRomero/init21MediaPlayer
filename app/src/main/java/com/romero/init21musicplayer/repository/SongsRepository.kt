package com.romero.init21musicplayer.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.romero.init21musicplayer.models.RequestCall
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.utils.Constants
import java.util.ArrayList

class SongsRepository {

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    fun convertToSong(cursor: Cursor): SongModel {

        val titleSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
        val durationSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
        val pathSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))

        // img
        val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
        val albumUri = Uri.parse(Constants.ALBUM_URI)
        val artUri = Uri.withAppendedPath(albumUri, albumId).toString()

        return SongModel(titleSong, durationSong, pathSong, artUri)

    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.Q)
    fun fetchAllSongs(c: ContentResolver): MutableLiveData<RequestCall> {

        val songs = ArrayList<SongModel>()

        val r = RequestCall()
        r.status = Constants.LOADING
        r.songs = songs

        val mLiveData = MutableLiveData<RequestCall>()
        mLiveData.value = r

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        val cursor = c.query(
            uri,
            projection,
            selection,
            null,
            sortOrder,
            null
        )

        if(cursor != null && cursor.moveToFirst()) {

            do {

                songs.add(convertToSong(cursor))

            } while (cursor.moveToNext())

            cursor.close()

        }

        r.status = Constants.STOPPED
        r.songs = songs

        mLiveData.postValue(r)

        return mLiveData

    }

}