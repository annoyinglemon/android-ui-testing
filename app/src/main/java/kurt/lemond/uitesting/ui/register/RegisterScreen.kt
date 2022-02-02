package kurt.lemond.uitesting.ui.register

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.editableText
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.composethemeadapter.MdcTheme
import kurt.lemond.uitesting.R

@Composable
fun RegisterScreen(
    userName: String,
    password: String,
    retypedPassword: String,
    onUsernameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onRetypePasswordChange: (String) -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        RegisterTextField(
            text = userName,
            label = R.string.label_username,
            modifier = Modifier.padding(8.dp),
            onTextChange = onUsernameChange
        )
        RegisterTextField(
            text = password,
            label = R.string.label_password,
            modifier = Modifier.padding(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onTextChange = onPasswordChange
        )
        RegisterTextField(
            text = retypedPassword,
            label = R.string.label_retype_password,
            modifier = Modifier.padding(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onTextChange = onRetypePasswordChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = userName.isNotBlank() && password.isNotBlank() && retypedPassword.isNotBlank()
        ) {
            Text(text = stringResource(id = R.string.button_register))
        }
    }
}

@Composable
fun RegisterTextField(
    text: String,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChange: (String) -> Unit = {}
) {
    val labelTag = stringResource(id = label)
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = labelTag) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .semantics {
               testTag = labelTag
            }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MdcTheme {
        RegisterScreen(
            "JohnDoe",
            "password",
            "password"
        )
    }
}