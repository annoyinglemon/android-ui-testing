package kurt.lemond.uitesting.dagger

import dagger.BindsInstance
import dagger.Component
import kurt.lemond.uitesting.dagger.fragment.FragmentsModule
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelsModule
import kurt.lemond.uitesting.ui.MainActivity

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ViewModelsModule::class, FragmentsModule::class]
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): ActivityComponent

        fun appComponent(appComponent: AppComponent?): Builder

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder

    }

}