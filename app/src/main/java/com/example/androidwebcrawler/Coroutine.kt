package com.example.androidwebcrawler

import com.example.androidwebcrawler.databinding.FragmentOneBinding
import com.example.mylistapplication.ContactsListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Coroutine {
    companion object {
        fun BackgroundTask(where: Int, adapter : ContactsListAdapter, notice : MutableList<NoticeListForm>){
            CoroutineScope(Dispatchers.Main).launch {
                async(Dispatchers.Default){
                    notice.addAll(JsoupCrawler().boardCrawling(where))
                }.await()
                adapter.notifyDataSetChanged()
            }
        }
        fun PostTask(where: Int, data_id: String) {
            CoroutineScope(Dispatchers.Main).launch {
                val str = async(Dispatchers.Default){
                    JsoupCrawler().postCrawling(where, data_id)
                }.await()
            }

        }
    }
}