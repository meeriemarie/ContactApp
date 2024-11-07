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
    suspend fun addContact(contactEntity: ContactEntity)

    @Update
    suspend fun updateContact(contactEntity: ContactEntity)

    @Delete
    suspend fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts")
    fun readAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name = :contactName")
    fun findContactsByName(contactName: String) : Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE telephone_number = :telephoneNumber ORDER BY name")
    fun findContactsByTelNum(telephoneNumber: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE age > 30 ORDER BY name")
    fun findContactsOver30(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE age = :age AND name LIKE '%' || :term || '%' ORDER BY name")
    fun findContactsByAgeAndNameContainingTerm(age: Int, term: String): Flow<List<ContactEntity>>
}