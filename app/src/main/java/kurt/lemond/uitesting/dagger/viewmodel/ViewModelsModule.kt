package kurt.lemond.uitesting.dagger.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kurt.lemond.uitesting.ui.contacts.ContactsViewModel
import kurt.lemond.uitesting.ui.login.LoginViewModel
import kurt.lemond.uitesting.ui.register.RegisterViewModel

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    abstract fun contactsViewModel(viewModel: ContactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun registerViewModel(viewModel: RegisterViewModel): ViewModel

}