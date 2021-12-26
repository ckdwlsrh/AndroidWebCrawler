package com.example.androidwebcrawler

import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidwebcrawler.databinding.FragmentOneBinding
import com.example.mylistapplication.ContactsListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class OneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOneBinding.inflate(inflater)
        var notice = mutableListOf<NoticeListForm>()
        val adapter = ContactsListAdapter(notice)
        binding.mRecyclerView.adapter = adapter
        Coroutine.BackgroundTask(1, adapter, notice)
        return binding.root
    }
}