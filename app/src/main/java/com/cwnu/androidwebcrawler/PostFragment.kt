package com.cwnu.androidwebcrawler

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.whenResumed
import com.cwnu.androidwebcrawler.databinding.FragmentPostBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PostFragment(where: Int, data_id: String) : DialogFragment() {
    val where = where
    val data_id = data_id
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPostBinding.inflate(inflater)
        var data = ""

        binding.post.movementMethod = ScrollingMovementMethod()
        CoroutineScope(Dispatchers.Main).launch {
            async(Dispatchers.Default){
                data = JsoupCrawler().postCrawling(where, data_id)
            }.await()
            binding.post.setText(HtmlCompat.fromHtml(data,HtmlCompat.FROM_HTML_MODE_COMPACT))
        }

        return binding.root
    }

}