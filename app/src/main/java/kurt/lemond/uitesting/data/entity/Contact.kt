package kurt.lemond.uitesting.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val number: String,
    val email: String,
    val occupation: String
): Parcelable