package kurt.lemond.uitesting.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.repo.AccountRepository

class RegisterViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _viewActions = Channel<ViewAction>(
        capacity = Channel.UNLIMITED,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val viewActions: ReceiveChannel<ViewAction> = _viewActions

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var retypedPassword by mutableStateOf("")
        private set

    fun onViewEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.UserNameChange -> username = event.username
            is ViewEvent.PasswordChange -> password = event.password
            is ViewEvent.RetypedPasswordChange -> retypedPassword = event.retypedPassword
            is ViewEvent.Register -> register()
        }
    }

    private fun register() = viewModelScope.launch {
        val registered = accountRepository.register(
            username = username,
            password = password,
            retypedPassword = retypedPassword
        )
        if (registered) {
            clearFields()
            sendViewAction(ViewAction.SnackBar(R.string.register_success))
        } else {
            sendViewAction(ViewAction.SnackBar(R.string.register_error))
        }
    }

    private fun sendViewAction(action: ViewAction) = viewModelScope.launch {
        _viewActions.send(action)
    }

    private fun clearFields() {
        onViewEvent(ViewEvent.UserNameChange(""))
        onViewEvent(ViewEvent.PasswordChange(""))
        onViewEvent(ViewEvent.RetypedPasswordChange(""))
    }

    sealed class ViewAction {
        data class SnackBar(@StringRes val messageRes: Int): ViewAction()
    }

    sealed class ViewEvent {
        object Register: ViewEvent()
        data class UserNameChange(
            val username: String
        ): ViewEvent()
        data class PasswordChange(
            val password: String
        ): ViewEvent()
        data class RetypedPasswordChange(
            val retypedPassword: String
        ): ViewEvent()
    }

}