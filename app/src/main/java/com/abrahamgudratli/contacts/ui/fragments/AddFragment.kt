package com.abrahamgudratli.contacts.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abrahamgudratli.contacts.R
import com.abrahamgudratli.contacts.data.Contact
import com.abrahamgudratli.contacts.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        view.btnUpdate.setOnClickListener {
            addContactToDatabase()
        }

        return view
    }

    private fun addContactToDatabase() {
        val firstName = etvUpdateFirstName.text.toString()
        val lastName = etvUpdateLastName.text.toString()
        val phoneNumber = etvUpdatePhone.text.toString()

        if (inputCheck(firstName, lastName, phoneNumber)) {
            val contact = Contact(0, firstName, lastName, phoneNumber)
            viewModel.addContact(contact)
            Toast.makeText(context, "Contact was added..", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Please fill all fields..", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(name: String, surname: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname) && TextUtils.isEmpty(phone))
    }

}