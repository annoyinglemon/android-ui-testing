package kurt.lemond.uitesting.data.repo

import javax.inject.Inject
import javax.inject.Singleton
import kurt.lemond.uitesting.data.entity.Contact
import kurt.lemond.uitesting.repo.ContactsRepository

private val contacts = listOf(
    Contact(
        name = "Julie Shubert",
        number = "419-235-6829",
        email = "devon2000@gmail.com",
        occupation = "Bill and Account Collector"
    ),
    Contact(
        name = "Terrell Porter",
        number = "252-883-8388",
        email = "bryana1989@gmail.com",
        occupation = "Computer Programmer"
    ),
    Contact(
        name = "Bertha Otto",
        number = "317-551-4111",
        email = "mayra.col9@yahoo.com",
        occupation = "Nursery and Greenhouse Manager"
    ),
    Contact(
        name = "Jose Jones",
        number = "801-710-5577",
        email = "bonita1989@hotmail.com",
        occupation = "Musician and Singer"
    ),
    Contact(
        name = "Sandra Franklin",
        number = "434-632-9641",
        email = "evan.huel3@hotmail.com",
        occupation = "Tax Preparer"
    ),
    Contact(
        name = "Daniel Carver",
        number = "336-317-1144",
        email = "fanny.croni8@gmail.com",
        occupation = "Librarian"
    ),
    Contact(
        name = "Jean Leavens",
        number = "317-374-7345",
        email = "makayla_joh@yahoo.com",
        occupation = "Natural Sciences Manager"
    ),
    Contact(
        name = "Frederick Goodman",
        number = "641-455-6411",
        email = "kaden.larki6@hotmail.com",
        occupation = "Registered Nurse"
    ),
    Contact(
        name = "Helen Roberts",
        number = "641-455-6411",
        email = "justina.wol@gmail.com",
        occupation = "Mapping Technician"
    ),
    Contact(
        name = "William Thompson",
        number = "269-533-0237",
        email = "lera1985@yahoo.com",
        occupation = "Sound Engineering Technician"
    ),
    Contact(
        name = "Katherine Hill",
        number = "209-212-4702",
        email = "christian2012@gmail.com",
        occupation = "Government Property Inspector and Investigator"
    ),
    Contact(
        name = "Charles Dalrymple",
        number = "270-705-8288",
        email = "wilhelm1986@hotmail.com",
        occupation = "Tool Grinder, Filer, and Sharpener"
    ),
)

@Singleton
class ContactsRepositoryImpl @Inject constructor(): ContactsRepository {

    override suspend fun getContacts(): List<Contact> = contacts.sortedBy { it.name }

}