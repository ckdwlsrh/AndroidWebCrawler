package com.cwnu.androidwebcrawler

import ContactsListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Coroutine {
    companion object {
        fun coroutineCoveredCrawling(currPage: String,where: Int, adapter : ContactsListAdapter, notice : MutableList<NoticeListForm>){
            CoroutineScope(Dispatchers.Main).launch {
                async(Dispatchers.Default){
                    notice.addAll(JsoupCrawler().boardCrawling(currPage, where))
                }.await()
                adapter.notifyDataSetChanged()
            }
        }
    }
}