package com.abrahamgudratli.contacts.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface ContactDao{

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)



}