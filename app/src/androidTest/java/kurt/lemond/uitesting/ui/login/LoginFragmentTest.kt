package kurt.lemond.uitesting.ui.login

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.TestData.PASSWORD
import kurt.lemond.uitesting.TestData.USERNAME
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.ui.core.FragmentTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest: FragmentTest() {

    @Suppress("UNCHECKED_CAST")
    override fun <F : Fragment> initializeFragment(): F {
        return LoginFragment(viewModelFactory = viewModelFactory) as F
    }

    override fun initializeNavigation(fragment: Fragment) {
        navController.setGraph(R.navigation.main_nav_graph)
        Navigation.setViewNavController(fragment.requireView(), navController)
    }

    @Test
    fun verifyLoginFields_AreVisible() {
        launchFragment<LoginFragment>()

        loginFragment {
            userNameFieldIsVisible()
            passwordFieldIsVisible()
            loginButtonIsVisible()
        }
    }

    @Test
    fun verifySnackBar_OnFailedLogin() {
        val accountRepo = mockk<AccountRepository> {
            coEvery { login(any(), any()) }.returns(false)
        }
        launchFragment<LoginFragment>(accountRepo = accountRepo)

        loginFragment {
            typeUsername(USERNAME)
            typePassword(PASSWORD)
            clickLogin()
            verifyLoginErrorSnackBar()
        }
    }

    @Test
    fun verifyNavigation_OnSuccessfulLogin() {
        val accountRepo = mockk<AccountRepository> {
            coEvery { login(any(), any()) }.returns(true)
        }
        launchFragment<LoginFragment>(accountRepo = accountRepo)

        loginFragment {
            typeUsername(USERNAME)
            typePassword(PASSWORD)
            clickLogin()
            verifyUserIsLoggedIn(navController)
        }
    }

    @Test
    fun verifyNavigation_OnRegisterClick() {
        val accountRepo = mockk<AccountRepository> {
            coEvery { login(any(), any()) }.returns(true)
        }
        launchFragment<LoginFragment>(accountRepo = accountRepo)

        loginFragment {
            clickRegister()
            verifyRegisterIsDisplayed(navController)
        }
    }

}