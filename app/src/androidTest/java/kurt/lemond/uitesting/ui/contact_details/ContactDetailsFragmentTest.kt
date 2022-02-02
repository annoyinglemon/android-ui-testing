package kurt.lemond.uitesting.ui.contact_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.TestData.CONTACTS
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.ui.core.FragmentTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactDetailsFragmentTest: FragmentTest() {

    private lateinit var contact: Contact

    @Suppress("UNCHECKED_CAST")
    override fun <F : Fragment> initializeFragment(): F {
        return ContactDetailsFragment() as F
    }

    @Before
    fun setup() {
        contact = CONTACTS.random()
        val bundleArg = Bundle().apply {
            putParcelable("contact", contact)
        }
        launchFragment<ContactDetailsFragment>(bundleArg = bundleArg)
    }

    @Test
    fun verifyContactDetails_AreDisplayed() {
        contactDetailsFragment {
            verifyDetailsAreShown(contact)
        }
    }
}