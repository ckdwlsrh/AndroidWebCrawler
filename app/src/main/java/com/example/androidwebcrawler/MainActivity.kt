
package com.example.androidwebcrawler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.example.androidwebcrawler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {
    fun post(where:Int, data_id: String) {
        PostFragment(where,data_id).show(supportFragmentManager,"post")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.view, PageFragment(5))
            .commit()
//잡스케줄러

        var jobScheduler : JobScheduler? = getSystemService<JobScheduler>()
        var builder = JobInfo.Builder(1, ComponentName(this, NotificationService::class.java))
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPeriodic(1200000)
            .setPersisted(true)
        jobScheduler!!.schedule(builder.build())



        binding.btn1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, PageFragment(1))
                .commit()
        }
        binding.btn2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, PageFragment(2))
                .commit()
        }
        binding.btn3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, PageFragment(3))
                .commit()
        }
        binding.btn4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, PageFragment(4))
                .commit()
        }
        binding.btn5.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, PageFragment(5))
                .commit()
        }
    }
}