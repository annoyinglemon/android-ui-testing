package kurt.lemond.uitesting.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kurt.lemond.uitesting.databinding.FragmentLoginBinding
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelFactory
import kurt.lemond.uitesting.ui.util.receiveWithLifecycle

class LoginFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : Fragment() {

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentLoginBinding
    private val navController: NavController
        get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel.viewActions.receiveWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED,
            receiver = ::onViewAction
        )

        binding.login.setOnClickListener {
            loginEvent()
            hideKeyboard()
        }

        binding.register.setOnClickListener {
            registerEvent()
        }

        return binding.root
    }

    private fun loginEvent() {
        viewModel.onViewEvent(
            LoginViewModel.ViewEvent.Login(
                username = binding.username.text?.toString() ?: "",
                password = binding.password.text?.toString() ?: ""
            )
        )
    }

    private fun registerEvent() {
        viewModel.onViewEvent(
            LoginViewModel.ViewEvent.Register
        )
    }

    private fun onViewAction(action: LoginViewModel.ViewAction) {
        when (action) {
            is LoginViewModel.ViewAction.DisplayScreen -> {
                navigateToScreen(action.screen)
            }
            is LoginViewModel.ViewAction.SnackBar -> {
                Snackbar.make(binding.root, action.messageRes, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToScreen(screen: LoginViewModel.Screen) {
        when (screen) {
            LoginViewModel.Screen.Contacts -> {
                navController.navigate(LoginFragmentDirections.displayContacts())
            }
            LoginViewModel.Screen.Register -> {
                navController.navigate(LoginFragmentDirections.displayRegister())
            }
        }
    }

    private fun hideKeyboard() = binding.root.run {
        val imm = getSystemService(requireActivity(), InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}