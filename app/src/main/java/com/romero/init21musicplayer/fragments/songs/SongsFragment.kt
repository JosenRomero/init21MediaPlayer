package com.romero.init21musicplayer.fragments.songs

import android.Manifest
import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentSongsBinding
import com.romero.init21musicplayer.models.SongModel

class SongsFragment : Fragment() {

    // FragmentSongsBinding - SongsFragment
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    private val songsAdapter = SongsAdapter()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSongsBinding.inflate(inflater, container, false)

        val myPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted ->

            if(isGranted) {

                initRecyclerView()

            }else {
                Toast.makeText(requireContext(), "You need accept permissions to use this app", Toast.LENGTH_SHORT).show()
            }

        }

        myPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun initRecyclerView() {

        val songs = getAllSongs()

        songsAdapter.setSongs(songs)

        binding.songsRecyclerview.adapter = songsAdapter
        binding.songsRecyclerview.layoutManager = LinearLayoutManager(activity)

    }


    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Recycle", "Range")
    private fun getAllSongs(): ArrayList<SongModel> {

        val songs = ArrayList<SongModel>()

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

        val cursor: Cursor? = requireActivity().contentResolver.query(
            uri,
            projection,
            selection,
            null,
            sortOrder,
            null
        )

        if(cursor != null && cursor.moveToFirst()) {

            do {

                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                val path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))

                // img
                val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                val albumUri = Uri.parse("content://media/external/audio/albumart")
                val artUri = Uri.withAppendedPath(albumUri, albumId).toString()

                songs.add(SongModel(title, duration, path, artUri))

            } while (cursor.moveToNext())

            cursor.close()

        }

        return songs

    }

}