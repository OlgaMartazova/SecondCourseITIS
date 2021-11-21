package com.itis.secondcourseitis

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.itis.secondcourseitis.databinding.ActivityClockBinding
import java.lang.Integer.parseInt
import java.util.*

class ClockActivity: AppCompatActivity() {
    private lateinit var binding: ActivityClockBinding
    private var service: NotificationService? = null
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        service = NotificationService(this)

//        val receiver = ComponentName(applicationContext, BootReceiver::class.java)
//
//        applicationContext.packageManager.setComponentEnabledSetting(
//            receiver,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            // or PackageManager.COMPONENT_ENABLED_STATE_DISABLED to disable it
//            PackageManager.DONT_KILL_APP
//        )


        binding.btnStart.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            pendingIntent = Intent(this, Receiver::class.java).let {
                    intent ->  PendingIntent.getBroadcast(this, 0, intent, 0)
            }
            val hour = binding.etHour.text.toString()
            val minute = binding.etMinute.text.toString()
            setTime(hour, minute)
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }

        binding.btnStop.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            pendingIntent = Intent(this, Receiver::class.java).let {
                    intent ->  PendingIntent.getBroadcast(this, 0, intent, 0)
            }
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun setTime(hour: String, minute: String) {
        calendar = Calendar.getInstance()
        if (hour != "") {
            calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        }
        if (minute != "") {
            calendar.set(Calendar.MINUTE, minute.toInt())
        }
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }
}
