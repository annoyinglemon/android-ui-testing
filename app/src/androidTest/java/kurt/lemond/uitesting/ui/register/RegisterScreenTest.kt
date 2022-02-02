package kurt.lemond.uitesting.ui.register

import androidx.compose.foundation.MutatePriority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties.EditableText
import androidx.compose.ui.test.SemanticsMatcher.Companion.expectValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasImeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.test.platform.app.InstrumentationRegistry
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.TestData.PASSWORD
import kurt.lemond.uitesting.TestData.USERNAME
import org.junit.Rule
import org.junit.Test


class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    val composeTestRule = createAndroidComposeRule<ComponentActivity>() // use this if you need access to activity

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    private val usernameLabel = resources.getString(R.string.label_username)
    private val passwordLabel = resources.getString(R.string.label_password)
    private val retypePasswordLabel = resources.getString(R.string.label_retype_password)
    private val registerText = resources.getString(R.string.button_register)

    @Test
    fun verifyRegistrationFieldsAreVisible() {
        composeTestRule.setContent {
            RegisterScreen(
                userName = "",
                password = "",
                retypedPassword = ""
            )
        }

        composeTestRule.onRoot().printToLog("UI_TEST")

        composeTestRule
            .onNodeWithText(usernameLabel)
            .assertIsDisplayed()
//
//        composeTestRule
//            .onNodeWithTag(passwordLabel)
//            .assertIsDisplayed()

        composeTestRule
            .onNode(hasText(passwordLabel) and hasImeAction(ImeAction.Default))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(registerText)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun verifyFieldsHaveTexts() {
        var username by mutableStateOf("")
        var password by mutableStateOf("")
        var retypePassword by mutableStateOf("")

        composeTestRule.setContent {
            RegisterScreen(
                userName = username,
                password = password,
                retypedPassword = retypePassword,
                onUsernameChange = { username = it },
                onPasswordChange = { password = it },
                onRetypePasswordChange = { retypePassword = it }
            )
        }

        composeTestRule
            .onNodeWithTag(usernameLabel)
            .performTextInput(USERNAME)

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .performTextInput(PASSWORD)

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .performTextInput(PASSWORD)

        composeTestRule
            .onNodeWithTag(usernameLabel)
            .assert(expectValue(
                EditableText, AnnotatedString(username)
            ))

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .assert(expectValue(
                EditableText, AnnotatedString(password)
            ))

        composeTestRule
            .onNodeWithTag(retypePasswordLabel)
            .assert(expectValue(
                EditableText, AnnotatedString(retypePassword)
            ))

        composeTestRule
            .onNodeWithText(registerText)
            .assertIsDisplayed()
            .assertIsEnabled()
    }

}