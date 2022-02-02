package kurt.lemond.uitesting.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.fueled.reclaim.ItemsViewAdapter
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelFactory
import kurt.lemond.uitesting.databinding.FragmentContactsBinding
import kurt.lemond.uitesting.ui.util.receiveWithLifecycle

class ContactsFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : Fragment() {

    private val viewModel: ContactsViewModel by viewModels {  viewModelFactory }
    private lateinit var binding: FragmentContactsBinding
    private val navController: NavController
        get() = findNavController()

    private val adapter = ItemsViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)

        binding.contacts.adapter = adapter
        binding.contacts.addItemDecoration(
            DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        )

        viewModel.state
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .onEach(::onStateReceive)
            .launchIn(viewLifecycleOwner.lifecycle.coroutineScope)

        viewModel.viewActions.receiveWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED,
            receiver = ::onViewAction
        )

        viewModel.onViewEvent(ContactsViewModel.ViewEvent.Init)

        return binding.root
    }

    private fun onStateReceive(state: ContactsViewModel.State) {
        val items = state.contacts.map {
            ContactItem(it) { contact ->
                viewModel.onViewEvent(
                    ContactsViewModel.ViewEvent.ViewDetails(contact)
                )
            }
        }
        adapter.replaceItems(items, true)
    }

    private fun onViewAction(action: ContactsViewModel.ViewAction) {
        if (action is ContactsViewModel.ViewAction.DisplayScreen) {
            displayScreen(action.screen)
        }
    }

    private fun displayScreen(screen: ContactsViewModel.Screen) {
        when (screen) {
            is ContactsViewModel.Screen.ContactDetails -> {
                navController.navigate(
                    ContactsFragmentDirections.displayDetails(screen.contact)
                )
            }
        }
    }

}