package kurt.lemond.uitesting

import androidx.multidex.MultiDexApplication
import kurt.lemond.uitesting.dagger.AppComponent
import kurt.lemond.uitesting.dagger.DaggerAppComponent

class UiTestingApplication : MultiDexApplication() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}