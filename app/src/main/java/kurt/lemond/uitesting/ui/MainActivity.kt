package kurt.lemond.uitesting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.UiTestingApplication
import kurt.lemond.uitesting.dagger.ActivityComponent
import kurt.lemond.uitesting.dagger.AppComponent
import kurt.lemond.uitesting.dagger.DaggerActivityComponent
import kurt.lemond.uitesting.dagger.fragment.DaggerFragmentFactory

class MainActivity : AppCompatActivity() {

    private val appComponent: AppComponent
        get() = (application as UiTestingApplication).component

    private val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .mainActivity(this)
            .build()
    }

    @Inject
    lateinit var fragmentFactory: DaggerFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}