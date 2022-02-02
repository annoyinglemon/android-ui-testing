package kurt.lemond.uitesting.ui.contact_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import javax.inject.Inject
import kurt.lemond.uitesting.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment @Inject constructor() : Fragment() {

    private val args: ContactDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentContactDetailsBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            val contact = args.contact
            fullName.text = contact.name
            phoneNumber.text = contact.number
            email.text = contact.email
            occupation.text = contact.occupation
        }.root
    }

}