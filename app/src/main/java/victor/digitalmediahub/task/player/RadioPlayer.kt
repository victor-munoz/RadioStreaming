package victor.digitalmediahub.task.player

interface RadioPlayer {

    fun setMetadataListener(listener: MetadataListener)
    fun removeMetadataListener()
    fun playRadio()
    fun stopRadio()
    fun release()
    fun isPlaying(): Boolean

    data class StreamMetadata(val artist: String, val song: String)

    interface MetadataListener {
        fun updateMetadata(metadata: StreamMetadata)
    }
}