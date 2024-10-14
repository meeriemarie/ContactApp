package at.ac.fhstp.contactsapp.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactRepository {
    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )
    private val _contacts = MutableStateFlow(createContacts())
    val contacts = _contacts.asStateFlow()

    fun createContacts(): List<Contact> {
        val contacts = (1..20).map {
            Contact(
                "${names.random()} $it",
                "+43 123456$it",
                25 + it
            )
        }
        /* another way of writing it
        val contactList = mutableListOf<Contact>()
        for (i in 1..15) {
            val contact = Contact(
                "Contact $i",
                "+43123456$i",
                25 + i
            )
            contactList.add(contact)
        } */
        return contacts
    }

    fun addRandomContact() {
        _contacts.update { oldList ->
            oldList + Contact(names.random(), "+4357894", 45)
        }
    }
}