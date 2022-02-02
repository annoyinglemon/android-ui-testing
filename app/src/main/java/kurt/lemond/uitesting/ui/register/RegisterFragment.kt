package kurt.lemond.uitesting.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.composethemeadapter.MdcTheme
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kurt.lemond.uitesting.dagger.viewmodel.ViewModelFactory
import kurt.lemond.uitesting.ui.util.receiveWithLifecycle

class RegisterFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : Fragment() {

    private val viewModel: RegisterViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MdcTheme {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()

                    LaunchedEffect(key1 = true) {
                        viewModel.viewActions.receiveWithLifecycle(
                            lifecycle = viewLifecycleOwner.lifecycle,
                            minActiveState = Lifecycle.State.STARTED,
                        ) { viewAction ->
                            when (viewAction) {
                                is RegisterViewModel.ViewAction.SnackBar -> {
                                    showSnackBar(
                                        coroutineScope = coroutineScope,
                                        scaffoldState = scaffoldState,
                                        action = viewAction
                                    )
                                }
                            }
                        }
                    }

                    RegisterScreenContent(
                        viewModel = viewModel,
                        scaffoldState = scaffoldState
                    )
                }
            }
        }
    }

    @Composable
    fun RegisterScreenContent(
        viewModel: RegisterViewModel,
        scaffoldState: ScaffoldState
    ) {
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            RegisterScreen(
                userName = viewModel.username,
                password = viewModel.password,
                retypedPassword = viewModel.retypedPassword,
                onUsernameChange = {
                    viewModel.onViewEvent(
                        RegisterViewModel.ViewEvent.UserNameChange(it)
                    )
                },
                onPasswordChange = {
                    viewModel.onViewEvent(
                        RegisterViewModel.ViewEvent.PasswordChange(it)
                    )
                },
                onRetypePasswordChange = {
                    viewModel.onViewEvent(
                        RegisterViewModel.ViewEvent.RetypedPasswordChange(it)
                    )
                },
                onRegisterClick = {
                    viewModel.onViewEvent(
                        RegisterViewModel.ViewEvent.Register
                    )
                }
            )
        }
    }

    private fun showSnackBar(
        coroutineScope: CoroutineScope,
        scaffoldState: ScaffoldState,
        action: RegisterViewModel.ViewAction.SnackBar
    ) = coroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
            message = getString(action.messageRes)
        )
    }

}
