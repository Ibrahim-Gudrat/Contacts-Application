package com.abrahamgudratli.contacts.repository

import androidx.lifecycle.LiveData
import com.abrahamgudratli.contacts.data.Contact
import com.abrahamgudratli.contacts.data.ContactDao

class ContactRepository(private val contactDao: ContactDao) {

    suspend fun addContact(contact: Contact) = contactDao.addContact(contact)
    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)
    suspend fun deleteUser(contact: Contact) = contactDao.deleteContact(contact)
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()


}