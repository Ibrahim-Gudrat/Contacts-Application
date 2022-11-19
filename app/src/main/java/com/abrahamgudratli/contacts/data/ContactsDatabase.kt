package com.abrahamgudratli.contacts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao

    companion object {
        @Volatile
        var INSTANCE: ContactsDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getContactsDatabase(context: Context): ContactsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(Any()) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "Contacts"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }



}