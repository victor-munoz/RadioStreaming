package victor.digitalmediahub.task.features.radioStreaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.Animatable
import android.view.View
import victor.digitalmediahub.task.App
import victor.digitalmediahub.task.R
import javax.inject.Inject

class RadioActivity : AppCompatActivity(), RadioContract.View, View.OnClickListener {

    @Inject lateinit var action: RadioContract.Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
    }

    override fun onResume() {
        super.onResume()
        id_button.setOnClickListener(this)
    }

    override fun onPause() {
        id_button.setOnClickListener(null)
        super.onPause()
    }

    override fun onDestroy() {
        action.release()
        super.onDestroy()
    }

    override fun updateArtist(artistName: String) {
        player_artist.text = artistName
    }

    override fun updateSong(songName: String) {
        player_song.text = songName
    }

    override fun onClick(view: View) {
        when {
            action.isPlaying() -> {
                animateStopToPlay()
                action.onStopPressed()
            }
            else -> {
                animatePlayToStop()
                action.onPlayPressed()
            }
        }
    }

    private fun injectDependencies() {
        (application as App).getRadioComponent(this).inject(this)
    }

    private fun animatePlayToStop(){
        id_button.setImageDrawable(getDrawable(R.drawable.play))
        (id_button.drawable as Animatable).start()
    }

    private fun animateStopToPlay(){
        id_button.setImageDrawable(getDrawable(R.drawable.pause))
        (id_button.drawable as Animatable).start()
    }

}
