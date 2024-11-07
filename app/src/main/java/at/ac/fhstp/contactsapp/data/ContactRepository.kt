package at.ac.fhstp.contactsapp.data

import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ContactRepository(private val contactsDao: ContactsDao) {
    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

    val contacts = contactsDao.readAllContacts().map { list ->
        list.map{ entity ->
            Contact(entity._id, entity.name, entity.telephoneNumber, entity.age)
        }
    }

    suspend fun addRandomContact() {
        contactsDao.addContact(ContactEntity(0,names.random(),"+4366412345", 23))
    }

}