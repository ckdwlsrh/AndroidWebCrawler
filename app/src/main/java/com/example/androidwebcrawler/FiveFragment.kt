package com.example.androidwebcrawler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidwebcrawler.databinding.FragmentOneBinding
import com.example.mylistapplication.ContactsListAdapter

class FiveFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOneBinding.inflate(inflater)
        var notice = mutableListOf<NoticeListForm>()
        val adapter = ContactsListAdapter( requireActivity(),notice)
        binding.mRecyclerView.adapter = adapter
        Coroutine.BackgroundTask(5, adapter, notice)
        binding.mRecyclerView.addItemDecoration(Decoration())
        return binding.root
    }
}