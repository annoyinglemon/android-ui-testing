package kurt.lemond.uitesting.ui.contacts

import android.view.View
import android.widget.Button
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.common.truth.Truth.assertThat
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.ui.core.BaseRobot
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun contactsFragment(func: ContactsFragmentRobot.() -> Unit) =
    ContactsFragmentRobot().apply { func() }

class ContactsFragmentRobot: BaseRobot() {

    fun clickContactItem(contact: Contact) {
        val itemMatcher = contact.toItemMatcher()
        scrollRecyclerViewToItem(R.id.contacts, itemMatcher)
        clickRecyclerViewItem(R.id.contacts, itemMatcher)
    }

    fun clickContactItemDetails(position: Int) {
        scrollRecyclerViewToPosition(R.id.contacts, position)
        clickRecyclerViewItemChildAtPosition(R.id.contacts, position, R.id.details)
    }

    fun verifyContactsCount(count: Int) {
        verifyRecyclerViewCount(R.id.contacts, count)
    }

    fun verifyContacts(contacts: List<Contact>) {
        contacts.forEachIndexed { index, contact ->
            scrollRecyclerViewToPosition(R.id.contacts, index)

            verifyRecyclerViewItemChildAtPosition(
                R.id.contacts,
                index,
                R.id.name,
                withText(contact.name)
            )

            verifyRecyclerViewItemChildAtPosition(
                R.id.contacts,
                index,
                R.id.occupation,
                withText(contact.occupation)
            )
        }
    }

    fun verifyDetailsIsDisplayed(
        testNavHostController: TestNavHostController,
        contact: Contact
    ) {
        verifyDestination(testNavHostController, R.id.contact_details_fragment)
        val args = testNavHostController.backStack.last().arguments
        val result = args?.get("contact") as Contact
        assertThat(result).isEqualTo(contact)
    }

    private fun Contact.toItemMatcher(): Matcher<View> {
        return allOf(
            hasDescendant(allOf(withId(R.id.name), withText(name))),
            hasDescendant(allOf(withId(R.id.occupation), withText(occupation))),
        )
    }

}