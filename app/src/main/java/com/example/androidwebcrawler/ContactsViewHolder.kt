package com.example.mylistapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwebcrawler.NoticeListForm
import kotlinx.android.synthetic.main.item_contacts.view.*


class ContactsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: NoticeListForm) {
        view.mNume.text = item.num
        view.mTitle.text = item.title
        view.mName.text = item.name
        view.mDate.text = item.date
        view.mDate_id.text = item.data_id
    }
}