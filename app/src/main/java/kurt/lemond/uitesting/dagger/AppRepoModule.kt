package kurt.lemond.uitesting.dagger

import dagger.Binds
import dagger.Module
import kurt.lemond.uitesting.data.repo.AccountRepositoryImpl
import kurt.lemond.uitesting.data.repo.ContactsRepositoryImpl
import kurt.lemond.uitesting.repo.AccountRepository
import kurt.lemond.uitesting.repo.ContactsRepository

@Module
abstract class AppRepoModule {

    @Binds
    abstract fun bindAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindContactsRepository(contactsRepository: ContactsRepositoryImpl): ContactsRepository

}