
package com.example.androidwebcrawler

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidwebcrawler.databinding.ActivityMainBinding
import com.example.mylistapplication.ContactsListAdapter

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btn1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, OneFragment())
                .commit()
        }
        binding.btn2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, TwoFragment())
                .commit()
        }
        binding.btn3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, ThreeFragment())
                .commit()
        }
    }
}