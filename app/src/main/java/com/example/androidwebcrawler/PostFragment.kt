package com.example.androidwebcrawler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.whenResumed
import com.example.androidwebcrawler.databinding.FragmentPostBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PostFragment(where: Int, data_id: String) : Fragment() {
    val where = where
    val data_id = data_id
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPostBinding.inflate(inflater)
        var data = ""

        CoroutineScope(Dispatchers.Main).launch {
            async(Dispatchers.Default){
                data = JsoupCrawler().postCrawling(where, data_id)
            }.await()
        }
        binding.post.setText(HtmlCompat.fromHtml(data,HtmlCompat.FROM_HTML_MODE_COMPACT))
        return binding.root
    }

}