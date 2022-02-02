package kurt.lemond.uitesting.ui.contact_details

import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.ui.core.BaseRobot

fun contactDetailsFragment(func: ContactDetailsFragmentRobot.() -> Unit) =
    ContactDetailsFragmentRobot().apply { func() }

class ContactDetailsFragmentRobot: BaseRobot() {

    fun verifyDetailsAreShown(contact: Contact) {
        verifyText(R.id.full_name, contact.name)
        verifyText(R.id.phone_number, contact.number)
        verifyText(R.id.email, contact.email)
        verifyText(R.id.occupation, contact.occupation)
    }

}