package com.example.androidwebcrawler

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder

class NotificationService : JobService() {
    companion object {
        private val TAG = "NotificationService"
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}