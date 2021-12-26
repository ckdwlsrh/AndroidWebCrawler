package com.example.androidwebcrawler

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
    }
}