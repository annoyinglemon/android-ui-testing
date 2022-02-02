package kurt.lemond.uitesting.ui.login

import androidx.navigation.testing.TestNavHostController
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.ui.core.BaseRobot

fun loginFragment(func: LoginFragmentRobot.() -> Unit) =
    LoginFragmentRobot().apply { func() }

class LoginFragmentRobot: BaseRobot() {

    fun clickLogin() {
        clickView(R.id.login)
    }

    fun clickRegister() {
        clickView(R.id.register)
    }

    fun loginButtonIsVisible() {
        viewIsVisible(R.id.login)
    }

    fun passwordFieldIsVisible() {
        viewIsVisible(R.id.til_password)
    }

    fun verifyLoginErrorSnackBar() {
        verifySnackBar(R.string.login_error)
    }

    fun typePassword(password: String) {
        typeOnEditText(R.id.password, password)
    }

    fun verifyRegisterIsDisplayed(testNavHostController: TestNavHostController) {
        verifyDestination(testNavHostController, R.id.register_fragment)
    }

    fun verifyUserIsLoggedIn(testNavHostController: TestNavHostController) {
        verifyDestination(testNavHostController, R.id.contacts_fragment)
    }

    fun typeUsername(username: String) {
        typeOnEditText(R.id.username, username)
    }

    fun userNameFieldIsVisible() {
        viewIsVisible(R.id.til_username)
    }

}