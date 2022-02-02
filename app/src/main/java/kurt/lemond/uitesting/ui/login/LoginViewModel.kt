package kurt.lemond.uitesting.ui.login

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.repo.AccountRepository

class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _viewActions = Channel<ViewAction>(
        capacity = Channel.UNLIMITED,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val viewActions: ReceiveChannel<ViewAction> = _viewActions

    fun onViewEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.Login -> loginAttempt(event)
            ViewEvent.Register -> {
                sendViewAction(ViewAction.DisplayScreen(Screen.Register))
            }
        }
    }

    private fun loginAttempt(loginEvent: ViewEvent.Login) = viewModelScope.launch {
        val loggedIn = accountRepository.login(
            loginEvent.username,
            loginEvent.password
        )
        if (loggedIn) {
            sendViewAction(ViewAction.DisplayScreen(Screen.Contacts))
        } else {
            sendViewAction(ViewAction.SnackBar(R.string.login_error))
        }
    }

    private fun sendViewAction(action: ViewAction) = viewModelScope.launch {
        _viewActions.send(action)
    }

    sealed class ViewAction {
        data class SnackBar(@StringRes val messageRes: Int) : ViewAction()
        data class DisplayScreen(val screen: Screen) : ViewAction()
    }

    sealed class ViewEvent {
        data class Login(val username: String, val password: String) : ViewEvent()
        object Register : ViewEvent()
    }

    sealed class Screen {
        object Contacts : Screen()
        object Register : Screen()
    }

}


