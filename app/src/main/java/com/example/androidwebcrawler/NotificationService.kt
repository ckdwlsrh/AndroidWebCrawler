package com.example.androidwebcrawler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class NotificationService : JobService() {
    companion object {
        private val TAG = "NotificationService"
    }
    val num = mutableListOf<NoticeListForm>()

    override fun onCreate() {
        super.onCreate()
        num.add(JsoupCrawler().boardCrawling(1).get(0))
        num.add(JsoupCrawler().boardCrawling(2).get(0))
        num.add(JsoupCrawler().boardCrawling(3).get(0))
        num.add(JsoupCrawler().boardCrawling(4).get(0))
        num.add(JsoupCrawler().boardCrawling(5).get(0))
    }
    override fun onStartJob(params: JobParameters?): Boolean {
        Thread(Runnable {
            // 공지사항들 비교
            for(i in 1..5) {
                val temp = JsoupCrawler().boardCrawling(i).get(0)
                if(!num.get(i-1).equals(temp)) {
                    //알림
                    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    val builder: NotificationCompat.Builder

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channelId = "changwon_notice"
                        val channelName = "Notice Crawler Channel"
                        val channel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)

                        channel.description = "Notice from CE/IE/Waggle page"
                        channel.setShowBadge(true)
                        val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        val audioAttributes = AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build()
                        channel.setSound(uri,audioAttributes)
                        channel.enableLights(true)
                        channel.lightColor = Color.RED
                        channel.enableVibration(true)
                        channel.vibrationPattern = longArrayOf(100,200,100,200)

                        manager.createNotificationChannel(channel)

                        builder = NotificationCompat.Builder(this, channelId)
                    }
                    else {
                        builder = NotificationCompat.Builder(this)
                    }
                    builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
                    builder.setWhen(System.currentTimeMillis())
                    var str = ""
                    when(i) {
                        1 -> str = "[컴퓨터공학과] 공지사항"
                        2 -> str = "[컴퓨터공학과] 자유게시판"
                        3 -> str = "[정보통신공학과] 공지사항"
                        4 -> str = "[정보통신공학과] 수업게시판"
                        5 -> str = "[와글 홈페이지] 공지사항"
                    }
                    builder.setContentTitle(str)
                    builder.setContentText(temp.title)

                    manager.notify(11,builder.build())
                }
            }
            //
            jobFinished(params, true)
        }).start()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}