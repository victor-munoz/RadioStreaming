package victor.digitalmediahub.task.dependencyInjection.modules

import dagger.Module
import dagger.Provides
import victor.digitalmediahub.task.features.radioStreaming.RadioContract
import victor.digitalmediahub.task.features.radioStreaming.RadioPresenter
import victor.digitalmediahub.task.player.RadioPlayer

@Module(includes = [(ExoPlayerModule::class)])
class RadioModule(private val listener: RadioContract.View) {

    @Provides
    internal fun providesRadioPresenter(radioPlayer: RadioPlayer): RadioContract.Action{
        return RadioPresenter(radioPlayer, listener)
    }
}