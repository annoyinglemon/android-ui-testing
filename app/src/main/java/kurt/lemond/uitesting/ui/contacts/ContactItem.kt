package kurt.lemond.uitesting.ui.contacts

import android.view.View
import com.fueled.reclaim.AdapterItem
import com.fueled.reclaim.BaseViewHolder
import kurt.lemond.uitesting.R
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.databinding.ItemContactBinding

class ContactItem(
    private val contact: Contact,
    private val onDetailsClick: (Contact) -> Unit
): AdapterItem<ContactViewHolder>() {

    override val layoutId: Int = R.layout.item_contact

    override fun onCreateViewHolder(view: View) = ContactViewHolder(view)

    override fun updateItemViews(viewHolder: ContactViewHolder) {
        with (viewHolder.binding) {
            name.text = contact.name
            occupation.text = contact.occupation
            details.setOnClickListener { onDetailsClick(contact) }
            root.setOnClickListener { onDetailsClick(contact) }
        }
    }
}

class ContactViewHolder(view: View): BaseViewHolder(view) {
    val binding: ItemContactBinding = ItemContactBinding.bind(view)
}