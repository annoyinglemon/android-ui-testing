package kurt.lemond.uitesting.data.repo

import javax.inject.Inject
import javax.inject.Singleton
import kurt.lemond.uitesting.repo.AccountRepository

private const val CORRECT_USERNAME = "fueledAndroid"
private const val CORRECT_PASSWORD = "Android"

@Singleton
class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    private var accountsRegistered: List<Pair<String, String>> = emptyList()

    override suspend fun login(
        username: String,
        password: String
    ): Boolean {
        return (CORRECT_USERNAME == username && CORRECT_PASSWORD == password) ||
           accountsRegistered.contains(Pair(username, password))
    }

    override suspend fun register(
        username: String,
        password: String,
        retypedPassword: String
    ): Boolean {
        if (password != retypedPassword) return false

        accountsRegistered = accountsRegistered + Pair(username, password)

        return  true
    }

}