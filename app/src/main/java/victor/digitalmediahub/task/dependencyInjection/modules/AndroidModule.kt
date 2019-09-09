package victor.digitalmediahub.task.dependencyInjection.modules
import android.content.Context

import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: Context) {

    @Provides
    internal fun provideContext():Context {
        return context
    }

}
