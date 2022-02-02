package kurt.lemond.uitesting.ui.contacts

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.TestData.CONTACTS
import kurt.lemond.uitesting.repo.ContactsRepository
import kurt.lemond.uitesting.ui.core.FragmentTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactsFragmentTest: FragmentTest() {

    @Suppress("UNCHECKED_CAST")
    override fun <F : Fragment> initializeFragment(): F {
        return ContactsFragment(viewModelFactory) as F
    }

    override fun initializeNavigation(fragment: Fragment) {
        navController.setGraph(R.navigation.main_nav_graph)
        navController.setCurrentDestination(R.id.contacts_fragment)
        Navigation.setViewNavController(fragment.requireView(), navController)
    }

    @Before
    fun setup() {
        val contactsRepo = mockk<ContactsRepository> {
            coEvery { getContacts() }.returns(CONTACTS)
        }
        launchFragment<ContactsFragment>(contactsRepo = contactsRepo)
    }

    @Test
    fun verifyContacts_AreDisplayed() {
        contactsFragment {
            verifyContactsCount(CONTACTS.size)
            verifyContacts(CONTACTS)
        }
    }

    @Test
    fun verifyDetailsAreDisplayed_onItemClick() {
        contactsFragment {
            val contact = CONTACTS.last()
            clickContactItem(contact)
            verifyDetailsIsDisplayed(navController, contact)
        }
    }

    @Test
    fun verifyDetailsAreDisplayed_onButtonClick() {
        contactsFragment {
            val randomContact = CONTACTS.random()
            clickContactItemDetails(CONTACTS.indexOf(randomContact))
            verifyDetailsIsDisplayed(navController, randomContact)
        }
    }

}