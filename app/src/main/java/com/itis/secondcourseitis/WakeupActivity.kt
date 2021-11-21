package com.itis.secondcourseitis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.itis.secondcourseitis.databinding.ActivityWakeupBinding

class WakeupActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWakeupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWakeupBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}
