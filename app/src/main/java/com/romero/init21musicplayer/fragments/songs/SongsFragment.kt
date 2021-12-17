package com.romero.init21musicplayer.fragments.songs

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.init21musicplayer.databinding.FragmentSongsBinding
import com.romero.init21musicplayer.fragments.player.PlayerFragment
import com.romero.init21musicplayer.models.RequestCall
import com.romero.init21musicplayer.viewmodel.SongsViewModel

class SongsFragment : Fragment() {

    // FragmentSongsBinding - SongsFragment
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    private val songsAdapter = SongsAdapter()

    var songsViewModel: SongsViewModel = PlayerFragment.songsViewModel

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

        getAllSongs()

        binding.songsRecyclerview.adapter = songsAdapter
        binding.songsRecyclerview.layoutManager = LinearLayoutManager(activity)

        songsViewModel.isNowPlayingVisible.value = true

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getAllSongs() {

        songsViewModel.fetchAllSongs(requireActivity().contentResolver).observe(viewLifecycleOwner, Observer { requestCall: RequestCall ->

            songsAdapter.setSongs(requestCall.songs)

            songsViewModel.listSongs = requestCall.songs

        })

    }

}