package kurt.lemond.uitesting.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kurt.lemond.uitesting.UiTestingApplication
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.repo.ContactsRepository

@Singleton
@Component(modules = [AppRepoModule::class])
interface AppComponent {

    val application: Application

    val accountRepository: AccountRepository

    val contactsRepository: ContactsRepository

    fun inject(uiTestingApplication: UiTestingApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

}