package com.example.androidwebcrawler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwebcrawler.databinding.FragmentPageBinding
import com.example.mylistapplication.ContactsListAdapter

class PageFragment(val where: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPageBinding.inflate(inflater)
        var notice = mutableListOf<NoticeListForm>()
        var currPage = 1
        val adapter = ContactsListAdapter( requireActivity(),notice)
        binding.mRecyclerView.adapter = adapter
        Coroutine.coroutineCoveredCrawling(currPage.toString(),where, adapter, notice)


        binding.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val last_position = (binding.mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val total = adapter.itemCount - 1
                if(total == last_position) {
                    currPage++
                    Coroutine.coroutineCoveredCrawling(currPage.toString(), where, adapter, notice)
                }
            }

        })

        binding.mRecyclerView.addItemDecoration(Decoration())



        return binding.root
    }
}