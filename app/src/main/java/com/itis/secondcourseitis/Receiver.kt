package com.itis.secondcourseitis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.material.snackbar.Snackbar

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val service = NotificationService(context)
        service.showNotification(context)
    }
}
