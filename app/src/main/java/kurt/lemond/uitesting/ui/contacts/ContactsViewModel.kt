package kurt.lemond.uitesting.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.repo.ContactsRepository

class ContactsViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    private val _viewActions = Channel<ViewAction>(
        capacity = Channel.UNLIMITED,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val viewActions: ReceiveChannel<ViewAction> = _viewActions

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun onViewEvent(event: ViewEvent) {
        when (event) {
            ViewEvent.Init -> fetchContacts()
            is ViewEvent.ViewDetails -> viewDetails(event)
        }
    }

    private fun fetchContacts() = viewModelScope.launch {
        val contacts = contactsRepository.getContacts()
        _state.value = State(contacts)
    }

    private fun viewDetails(event: ViewEvent.ViewDetails) {
        sendViewAction(
            ViewAction.DisplayScreen(
                Screen.ContactDetails(event.contact)
            )
        )
    }

    private fun sendViewAction(action: ViewAction) = viewModelScope.launch {
        _viewActions.send(action)
    }

    data class State(
        val contacts: List<Contact> = emptyList()
    )

    sealed class ViewAction {
        data class DisplayScreen(val screen: Screen) : ViewAction()
    }

    sealed class ViewEvent {
        object Init: ViewEvent()
        data class ViewDetails(val contact: Contact) : ViewEvent()
    }

    sealed class Screen {
        data class ContactDetails(val contact: Contact) : Screen()
    }

}