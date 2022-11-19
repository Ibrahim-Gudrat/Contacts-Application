package com.abrahamgudratli.contacts.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abrahamgudratli.contacts.R
import com.abrahamgudratli.contacts.data.Contact
import com.abrahamgudratli.contacts.ui.fragments.ListFragment
import com.abrahamgudratli.contacts.ui.fragments.ListFragmentDirections
import com.abrahamgudratli.contacts.viewmodel.ContactViewModel
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactListAdapter() : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {

    private var contactList = emptyList<Contact>()

    inner class ContactListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.itemView.tvFullName.text = "${currentItem.name} ${currentItem.surname}"
        holder.itemView.tvPhoneNumber.text = currentItem.phoneNumber.toString()

        holder.itemView.constraintLayoutContactItem.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(contacts: List<Contact>) {
        this.contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


}