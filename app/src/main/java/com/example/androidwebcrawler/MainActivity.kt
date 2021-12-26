
package com.example.androidwebcrawler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.example.androidwebcrawler.databinding.ActivityMainBinding
import com.example.mylistapplication.ContactsListAdapter

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//잡스케줄러

        var jobScheduler : JobScheduler? = getSystemService<JobScheduler>()
        var builder = JobInfo.Builder(1, ComponentName(this, NotificationService::class.java))
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPeriodic(1200000)
            .setPersisted(true)
        jobScheduler!!.schedule(builder.build())



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
        binding.btn4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FourFragment())
                .commit()
        }
        binding.btn5.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FiveFragment())
                .commit()
        }
    }
}