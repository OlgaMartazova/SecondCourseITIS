package com.itis.secondcourseitis.service

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.itis.secondcourseitis.MainActivity
import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.fragment.TrackInfoFragment
import com.itis.secondcourseitis.repository.TrackRepository

private const val CHANNEL_ID = "channel_id_2"


class NotificationService(
    private val context: Context
) {

    private var builder:NotificationCompat.Builder

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_title),
                IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notification_channel_desc)

            }.also {
                manager.createNotificationChannel(it)
            }
        }

        val previous = Intent(context, MusicService::class.java).apply {
            action = "PREVIOUS"
        }

        val pause = Intent(context, MusicService::class.java).apply {
            action = "PAUSE"
        }

        val next = Intent(context, MusicService::class.java).apply {
            action = "NEXT"
        }


        val prevIntent = PendingIntent.getService(
            context,
            0,
            previous,
            0
        )

        val pauseIntent = PendingIntent.getService(
            context,
            1,
            pause,
            0
        )

        val nextIntent = PendingIntent.getService(
            context,
            2,
            next,
            0
        )



        //играет - две палочки
        //на паузе - треугольничек

        builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_play_arrow_24)
            .addAction(R.drawable.ic_baseline_skip_previous_24, "PREVIOUS", prevIntent)
            .addAction(R.drawable.ic_baseline_pause_24, "PAUSE", pauseIntent)
            .addAction(R.drawable.ic_baseline_skip_next_24, "NEXT", nextIntent)
    }

    fun setNotification(currentTrackId:Int) {
        val track = TrackRepository.tracks[currentTrackId]
        val icon = BitmapFactory.decodeResource(context.resources,track.cover)

        val bundle = Bundle().apply {
            putInt("idSong", currentTrackId)
        }

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.fragment_detail)
            .setArguments(bundle)
            .createPendingIntent()

        val updBuilder = builder
            .setContentTitle(track.title)
            .setContentText(track.author)
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setShowWhen(false)
            .setAutoCancel(false)
            .setSilent(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        manager.notify(1, updBuilder.build())
    }
}
