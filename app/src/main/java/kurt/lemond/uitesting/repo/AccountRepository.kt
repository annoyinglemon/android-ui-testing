package kurt.lemond.uitesting.repo

interface AccountRepository {

    /**
     *  returns true if user is logged in successfully, false otherwise
     */
    suspend fun login(username: String, password: String): Boolean

    /**
     *  returns true if user is registered successfully, false otherwise
     */
    suspend fun register(username: String, password: String, retypedPassword: String): Boolean

}