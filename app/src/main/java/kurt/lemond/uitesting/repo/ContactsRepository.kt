package kurt.lemond.uitesting.repo

import kurt.lemond.uitesting.data.entity.Contact

interface ContactsRepository {

    /**
     *  returns a list of contacts of the logged in user
     */
    suspend fun getContacts(): List<Contact>

}