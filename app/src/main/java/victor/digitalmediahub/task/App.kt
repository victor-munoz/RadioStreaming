package victor.digitalmediahub.task

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import victor.digitalmediahub.task.dependencyInjection.components.AppComponent
import victor.digitalmediahub.task.dependencyInjection.components.DaggerAppComponent
import victor.digitalmediahub.task.dependencyInjection.components.RadioComponent
import victor.digitalmediahub.task.dependencyInjection.modules.AndroidModule
import victor.digitalmediahub.task.dependencyInjection.modules.RadioModule
import victor.digitalmediahub.task.features.radioStreaming.RadioContract

class App : Application() {

    private lateinit var appComponent: AppComponent

    companion object {
        const val RADIO_URL="http://stream.antenne.com/antenne-nds-80er/mp3-128/app/"
    }

    override fun onCreate() {
        super.onCreate()
        setLeakCanary()
        createAppComponent()
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder().androidModule(AndroidModule(this)).build()
    }

    fun getRadioComponent(listener: RadioContract.View): RadioComponent{
        return appComponent.plus(RadioModule(listener))
    }

    private fun setLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }


}