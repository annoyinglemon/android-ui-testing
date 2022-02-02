package kurt.lemond.uitesting

import kurt.lemond.uitesting.data.entity.Contact


object TestData {

    const val USERNAME = "Fueled"
    const val PASSWORD = "Hydrogen"

    val CONTACTS = listOf(
        Contact(
            name = "Kimberly Brooks",
            number = "214-318-8573",
            email = "itzel1993@gmail.com",
            occupation = "Dental Hygienist"
        ),
        Contact(
            name = "Theodore McNeil",
            number = "818-317-2787",
            email = "leland2008@yahoo.com",
            occupation = "Security Guard"
        ),
        Contact(
            name = "Mary Gragg",
            number = "213-406--6498",
            email = "gerard2013@gmail.com",
            occupation = "Machine Operator"
        ),
        Contact(
            name = "Joseph Palmquist",
            number = "832-341-2125",
            email = "efren.jerd0@yahoo.com",
            occupation = "Biological Scientist"
        ),
        Contact(
            name = "Margaret Riddick",
            number = "408-210-9335",
            email = "garland_pri@hotmail.com",
            occupation = "Religion Teacher"
        ),
        Contact(
            name = "Thad Golden",
            number = "843-635-5254",
            email = "taurean_run@gmail.com",
            occupation = "Earth Driller"
        ),
        Contact(
            name = "Ashley Williams",
            number = "701-740-0739",
            email = "thelma.cormi@yahoo.com",
            occupation = "Energy Broker"
        ),
        Contact(
            name = "Mitchell Piner",
            number = "803-352-8845",
            email = "savanah_osins@hotmail.com",
            occupation = "Fundraising Manager"
        ),
        Contact(
            name = "Steven Rabago",
            number = "314-477-9430",
            email = "vern2016@yahoo.com",
            occupation = "Fiberglass Laminator and Fabricator"
        ),
        Contact(
            name = "Alice Horn",
            number = "210-990-2153",
            email = "alanis_brek@yahoo.com",
            occupation = "Exercise Physiologist"
        ),
        Contact(
            name = "Ernest Nagy",
            number = "510-310-4136",
            email = "rafaela2006@hotmail.com",
            occupation = "Fundraising Manager"
        ),
        Contact(
            name = "Tony Lane",
            number = "813-495-7595",
            email = "electa.veu0@yahoo.com",
            occupation = "Tactical Operations Leader"
        ),
        Contact(
            name = "Ana Daniels",
            number = "323-650--3371",
            email = "connie1982@yahoo.com",
            occupation = "Information and Record Clerk"
        ),
    ).sortedBy { it.name }

}
