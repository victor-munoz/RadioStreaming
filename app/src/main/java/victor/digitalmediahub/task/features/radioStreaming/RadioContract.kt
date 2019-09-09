package victor.digitalmediahub.task.features.radioStreaming

interface RadioContract {


        interface Action {
            fun onPlayPressed()
            fun onStopPressed()
            fun isPlaying():Boolean
            fun release()
        }

        interface View {
            fun updateArtist(artistName: String)
            fun updateSong(songName: String)

        }
}