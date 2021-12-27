package com.romero.init21musicplayer.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romero.init21musicplayer.models.AlbumModel
import com.romero.init21musicplayer.models.SongModel

object Utils {

    fun createTimeLabel(time: Int): String {

        val min = time / 1000 / 60
        val sec = time / 1000 % 60

        var timeLabel = "$min:"

        if(sec < 10) timeLabel += "0"

        timeLabel += sec

        return timeLabel

    }

    fun loadImg(uri: String, imgPlaceHolder: Int, view: ImageView) {
        Glide.with(view.context)
            .load(uri)
            .apply(RequestOptions())
            .placeholder(imgPlaceHolder)
            .centerCrop()
            .into(view)
    }

    @SuppressLint("Range")
    fun getAlbumArtUri(cursor: Cursor): String {

        val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
        val albumUri = Uri.parse(Constants.ALBUM_URI)

        return Uri.withAppendedPath(albumUri, albumId).toString()

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    fun getSong(cursor: Cursor): SongModel {

        val titleSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
        val durationSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
        val pathSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))

        val artUri = getAlbumArtUri(cursor)

        return SongModel(titleSong, durationSong, pathSong, artUri)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("Range")
    fun getAlbum(cursor: Cursor): AlbumModel {

        val nameAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
        var artistAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
        if(artistAlbum == "<unknown>") artistAlbum = "Unknown Artist"

        val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
        val artUri = getAlbumArtUri(cursor)

        return AlbumModel(albumId, nameAlbum, artistAlbum, artUri)

    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.Q)
    fun querySongs(c: ContentResolver): Cursor? {

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

        return c.query(
            uri,
            projection,
            selection,
            null,
            sortOrder,
            null
        )

    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.R)
    fun queryAlbums(c: ContentResolver): Cursor? {

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.ALBUM} ASC"

        return c.query(
            uri,
            projection,
            selection,
            null,
            sortOrder,
            null
        )

    }

}