package victor.digitalmediahub.task.dependencyInjection.components

import dagger.Subcomponent
import victor.digitalmediahub.task.features.radioStreaming.RadioActivity
import victor.digitalmediahub.task.dependencyInjection.modules.RadioModule

@Subcomponent(modules = [(RadioModule::class)])
interface RadioComponent {

    fun inject(activity: RadioActivity)
}