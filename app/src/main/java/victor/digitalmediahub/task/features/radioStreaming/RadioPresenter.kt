package victor.digitalmediahub.task.features.radioStreaming

import victor.digitalmediahub.task.player.RadioPlayer

class RadioPresenter(
    private var radioPlayer: RadioPlayer,
    private var view: RadioContract.View
) : RadioContract.Action, RadioPlayer.MetadataListener {

    override fun updateMetadata(metadata: RadioPlayer.StreamMetadata) {
        view.updateArtist(metadata.artist)
        view.updateSong(metadata.song)
    }

    override fun release() {
        radioPlayer.removeMetadataListener()
        radioPlayer.stopRadio()
        radioPlayer.release()
    }

    override fun onPlayPressed() {
        radioPlayer.playRadio()
        radioPlayer.setMetadataListener(this)
    }

    override fun onStopPressed() {
        radioPlayer.removeMetadataListener()
        radioPlayer.stopRadio()
    }

    override fun isPlaying(): Boolean {
        return radioPlayer.isPlaying()
    }
}