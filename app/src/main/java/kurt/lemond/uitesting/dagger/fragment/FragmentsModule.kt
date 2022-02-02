package kurt.lemond.uitesting.dagger.fragment

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kurt.lemond.uitesting.ui.contact_details.ContactDetailsFragment
import kurt.lemond.uitesting.ui.login.LoginFragment
import kurt.lemond.uitesting.ui.contacts.ContactsFragment
import kurt.lemond.uitesting.ui.register.RegisterFragment

@Module
abstract class FragmentsModule {

    @Binds
    @IntoMap
    @FragmentMapKey(LoginFragment::class)
    abstract fun provideLoginFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentMapKey(ContactsFragment::class)
    abstract fun provideContactsFragment(fragment: ContactsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentMapKey(ContactDetailsFragment::class)
    abstract fun provideContactDetailsFragment(fragment: ContactDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentMapKey(RegisterFragment::class)
    abstract fun provideRegisterFragment(fragment: RegisterFragment): Fragment

}