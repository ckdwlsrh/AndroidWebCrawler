package com.example.androidwebcrawler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidwebcrawler.databinding.FragmentOneBinding
import com.example.mylistapplication.ContactsListAdapter

class ThreeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOneBinding.inflate(inflater)
        var notice = mutableListOf<NoticeListForm>()
        val adapter = ContactsListAdapter(notice)
        binding.mRecyclerView.adapter = adapter
        Coroutine.BackgroundTask(3, adapter, notice)
        binding.mRecyclerView.addItemDecoration(Decoration())
        return binding.root
    }
}