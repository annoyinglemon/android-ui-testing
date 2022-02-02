package kurt.lemond.uitesting.ui.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import io.mockk.mockk
import javax.inject.Inject
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.dagger.fragment.DaggerFragmentTestComponent
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelFactory
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.repo.ContactsRepository

abstract class FragmentTest {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    @Inject
    protected lateinit var navController: TestNavHostController

    inline fun <reified T: Fragment> launchFragment(
        accountRepo: AccountRepository = mockk(),
        contactsRepo: ContactsRepository = mockk(),
        bundleArg: Bundle? = null
    ): FragmentScenario<T> {
        initializeTestComponent(accountRepo, contactsRepo)

        return launchFragmentInContainer<T>(
            themeResId = R.style.Theme_UiTesting,
            fragmentArgs = bundleArg,
        ) {
            initializeFragment()
        }.apply {
           onFragment { initializeNavigation(it) }
        }
    }

    fun initializeTestComponent(
        accountRepo: AccountRepository = mockk(),
        contactsRepo: ContactsRepository = mockk()
    ) {
        DaggerFragmentTestComponent
            .builder()
            .testNavController(
                TestNavHostController(ApplicationProvider.getApplicationContext())
            )
            .accountRepository(accountRepo)
            .contactsRepository(contactsRepo)
            .build()
            .inject(this)
    }

    /**
     *  Initializes the Fragment under test
     */
    abstract fun <F: Fragment> initializeFragment(): F

    open fun initializeNavigation(fragment: Fragment) {}

}