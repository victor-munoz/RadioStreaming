package victor.digitalmediahub.task.dependencyInjection.modules

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import victor.digitalmediahub.task.App.Companion.RADIO_URL
import victor.digitalmediahub.task.R
import victor.digitalmediahub.task.player.ExoPlayerImpl
import victor.digitalmediahub.task.player.RadioPlayer

@Module (includes = [(AndroidModule::class)])
class ExoPlayerModule {

    @Provides
    internal fun provideDefaultDataSourceFactory(context: Context): DefaultDataSourceFactory {
        return DefaultDataSourceFactory(context, Util.getUserAgent(context, context.resources.getString(R.string.app_name)))
    }
    @Provides
    internal fun providesProgressiveMediaSource(dataSourceFactory:DefaultDataSourceFactory): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory) .createMediaSource(Uri.parse(RADIO_URL))
    }
    @Provides
    internal fun providesSimpleExoPlayer(context: Context, mediaSource: MediaSource): SimpleExoPlayer{
        val player = ExoPlayerFactory.newSimpleInstance(context)
        player.prepare(mediaSource)
        return player
    }
    @Provides
    internal fun providesRadioPlayer(exoPlayer:SimpleExoPlayer): RadioPlayer {
        return ExoPlayerImpl(exoPlayer)
    }
}