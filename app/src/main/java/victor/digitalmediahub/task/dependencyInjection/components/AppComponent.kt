package victor.digitalmediahub.task.dependencyInjection.components

import dagger.Component
import victor.digitalmediahub.task.dependencyInjection.modules.AndroidModule
import victor.digitalmediahub.task.dependencyInjection.modules.RadioModule

@Component(modules = [(AndroidModule::class)])
interface AppComponent {

    fun plus(allUsersModule: RadioModule):RadioComponent

}