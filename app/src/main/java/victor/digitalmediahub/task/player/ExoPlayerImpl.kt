package victor.digitalmediahub.task.player

import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.metadata.MetadataOutput
import com.google.android.exoplayer2.metadata.icy.IcyInfo

class ExoPlayerImpl(private var player: SimpleExoPlayer) : RadioPlayer, MetadataOutput {

    private var listener: RadioPlayer.MetadataListener? = null

    override fun setMetadataListener(listener: RadioPlayer.MetadataListener) {
        this.listener = listener
        player.addMetadataOutput(this)
    }

    override fun removeMetadataListener() {
        player.removeMetadataOutput(this)
        this.listener = null
    }

    override fun onMetadata(metadata: Metadata) {
        listener?.run {
            val icyInfo = metadata[0] as IcyInfo
            val artist = icyInfo.title?.substringBefore('-')?:" - - "
            val song = icyInfo.title?.substringAfter('-')?:" - - "
            val streamMetadata = RadioPlayer.StreamMetadata(artist, song)
            updateMetadata(streamMetadata)
        }
    }

    override fun isPlaying(): Boolean {
        return player.playWhenReady
    }

    override fun playRadio() {
        player.playWhenReady = true
    }

    override fun stopRadio() {
        player.playWhenReady = false
    }

    override fun release() {
        player.release()
    }



}