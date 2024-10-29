package at.ac.fhstp.contactsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert
    fun addContact(contactEntity: ContactEntity)

    @Update
    fun updateContact(contactEntity: ContactEntity)

    @Delete
    fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts")
    fun readAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name = :contactName")
    fun findContactsByName(contactName: String)

    @Query("SELECT * FROM contacts WHERE telephone_number = :telephoneNumber ORDER BY name")
    fun findContactsByTelNum(telephoneNumber: String): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE age > 30 ORDER BY name")
    fun findContactsOver30(): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE age = :age AND name LIKE '%' || :term || '%' ORDER BY name")
    fun findContactsByAgeAndNameContainingTerm(age: Int, term: String): List<ContactEntity>
}