package com.example.mylistapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contacts.view.*


class ContactsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: Contacts) {
        view.mNume.text = item.num
        view.mTitle.text = item.title
        view.mDepart.text = item.depart
        view.mDate.text = item.date
        view.mDate_id.text = item.data_id
    }
}