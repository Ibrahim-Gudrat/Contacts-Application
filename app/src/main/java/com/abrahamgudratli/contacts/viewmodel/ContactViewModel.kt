package com.abrahamgudratli.contacts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrahamgudratli.contacts.data.Contact
import com.abrahamgudratli.contacts.data.ContactDao
import com.abrahamgudratli.contacts.data.ContactsDatabase
import com.abrahamgudratli.contacts.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Contact>>
    private val repository: ContactRepository

    init {
        val contactDao = ContactsDatabase.getContactsDatabase(application).getContactDao()
        repository = ContactRepository(contactDao)
        readAllData = repository.allContacts
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContact(contact)
        }
    }



}