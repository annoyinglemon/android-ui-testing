package kurt.lemond.uitesting.dagger.fragment

import androidx.navigation.testing.TestNavHostController
import dagger.BindsInstance
import dagger.Component
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelsModule
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.repo.ContactsRepository
import kurt.lemond.uitesting.ui.core.FragmentTest

@Component(modules = [ViewModelsModule::class])
interface FragmentTestComponent {

    fun inject(fragmentTest: FragmentTest)

    @Component.Builder
    interface Builder {

        fun build(): FragmentTestComponent

        @BindsInstance
        fun testNavController(testNavHostController: TestNavHostController): Builder

        @BindsInstance
        fun accountRepository(accountRepository: AccountRepository): Builder

        @BindsInstance
        fun contactsRepository(contactsRepository: ContactsRepository): Builder
    }

}