package com.abrahamgudratli.contacts.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abrahamgudratli.contacts.R
import com.abrahamgudratli.contacts.data.Contact
import com.abrahamgudratli.contacts.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        view.etvUpdateFirstName.setText(args.currentContact.name)
        view.etvUpdateLastName.setText(args.currentContact.surname)
        view.etvUpdatePhone.setText(args.currentContact.phoneNumber)

        view.btnUpdate.setOnClickListener {
            updateContact()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateContact() {
        val firstName = etvUpdateFirstName.text.toString()
        val lastName = etvUpdateLastName.text.toString()
        val phone = etvUpdatePhone.text.toString()

        if (inputCheck(firstName, lastName, phone)) {
            val updatedContact = Contact(args.currentContact.id, firstName, lastName, phone)
            viewModel.updateContact(updatedContact)

            Toast.makeText(requireContext(), "Contact was updated..", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all the fields, please..", Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputCheck(name: String, surname: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname) && TextUtils.isEmpty(phone))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteContact()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteContact() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteContact(args.currentContact)
            Toast.makeText(requireContext(), "Contact is deleted..", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete Contact")
        builder.setMessage("Do you want to delete ${args.currentContact.name}")
        builder.show()
    }


}