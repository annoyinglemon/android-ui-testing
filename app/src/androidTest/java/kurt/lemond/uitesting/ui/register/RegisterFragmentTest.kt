package kurt.lemond.uitesting.ui.register

import androidx.compose.ui.semantics.SemanticsProperties.EditableText
import androidx.compose.ui.test.SemanticsMatcher.Companion.expectValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.text.AnnotatedString
import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.coEvery
import io.mockk.mockk
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.TestData.PASSWORD
import kurt.lemond.uitesting.TestData.USERNAME
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.ui.core.FragmentTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterFragmentTest : FragmentTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    private val usernameLabel = resources.getString(R.string.label_username)
    private val passwordLabel = resources.getString(R.string.label_password)
    private val retypePasswordLabel = resources.getString(R.string.label_retype_password)
    private val registerText = resources.getString(R.string.button_register)
    private val registerSuccess = resources.getString(R.string.register_success)
    private val registerFailed = resources.getString(R.string.register_error)

    @Suppress("UNCHECKED_CAST")
    override fun <F : Fragment> initializeFragment(): F {
        return RegisterFragment(viewModelFactory = viewModelFactory) as F
    }

    @Test
    fun verifyFieldsAreCleared_snackbarIsShown_onSuccessfulRegistration() {
        val accountRepo = mockk<AccountRepository> {
            coEvery { register(any(), any(), any()) }.returns(true)
        }

        launchFragment<RegisterFragment>(accountRepo = accountRepo)

        composeTestRule
            .onNodeWithTag(usernameLabel)
            .performTextInput(USERNAME)

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .performTextInput(PASSWORD)

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .performTextInput(PASSWORD)

        onView(isRoot()).perform(closeSoftKeyboard())

        composeTestRule
            .onNodeWithText(registerText)
            .performClick()

        composeTestRule.onRoot().printToLog("UI_TEST")

        composeTestRule
            .onNodeWithText(registerSuccess)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(usernameLabel)
            .assert(expectValue(EditableText, AnnotatedString("")))

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .assert(expectValue(EditableText, AnnotatedString("")))

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .assert(expectValue(EditableText, AnnotatedString("")))
    }

    @Test
    fun verifyFieldsSnackbarIsShown_onFailedRegistration() {
        val accountRepo = mockk<AccountRepository> {
            coEvery { register(any(), any(), any()) }.returns(false)
        }

        launchFragment<RegisterFragment>(accountRepo = accountRepo)

        composeTestRule
            .onNodeWithTag(usernameLabel)
            .performTextInput(USERNAME)

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .performTextInput(PASSWORD)

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .performTextInput(PASSWORD)

        onView(isRoot()).perform(closeSoftKeyboard())

        composeTestRule
            .onNodeWithText(registerText)
            .performClick()

        composeTestRule
            .onNodeWithText(registerFailed)
            .assertIsDisplayed()
    }


}