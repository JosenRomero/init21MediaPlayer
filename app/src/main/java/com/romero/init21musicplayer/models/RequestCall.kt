package com.romero.init21musicplayer.models

import com.romero.init21musicplayer.utils.Constants
import java.util.ArrayList

class RequestCall {
    var status = Constants.STOPPED
    var songs = ArrayList<SongModel>()
}