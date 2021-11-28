package com.romero.init21musicplayer.fragments.songs

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentSongsBinding

class SongsFragment : Fragment() {

    // FragmentSongsBinding - SongsFragment
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSongsBinding.inflate(inflater, container, false)

        val myPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted ->

            if(isGranted) {
                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(requireContext(), "You need accept permissions to use this app", Toast.LENGTH_SHORT).show()
            }

        }

        myPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)


        return binding.root
    }

}