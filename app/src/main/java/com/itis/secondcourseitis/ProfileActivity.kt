package com.itis.secondcourseitis

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View.*
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.itis.secondcourseitis.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initListeners()
        sendIntentListener()

        //for view
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.transparant)
    }


    private fun initListeners() {
        with(binding) {
            btnEdit.setOnClickListener {
                btnEdit.visibility = INVISIBLE
                btnEdit.isClickable = false
                btnAccept.visibility = VISIBLE
                btnAccept.isClickable = true

                tvUserName.visibility = INVISIBLE
                etUserName.text = etUserName.text
                etUserName.visibility = VISIBLE

                btnSaved.visibility = INVISIBLE
            }
        }

        with(binding) {
            btnAccept.setOnClickListener {
                btnAccept.visibility = INVISIBLE
                btnAccept.isClickable = false
                btnEdit.visibility = VISIBLE
                btnEdit.isClickable = true

                etUserName.visibility = GONE

                tvUserName.text = etUserName.text
                tvUserName.visibility = VISIBLE

                btnSaved.visibility = VISIBLE
            }
        }
    }

    private fun sendIntentListener() {
        val shareProfileLink = "https://vm.tiktok.com/ZSeLshjEt/";

        binding.btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareProfileLink)
                type = "text/plain"
            }
            //Verify that the intent will resolve to an activity
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(sendIntent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Snackbar.make(binding.root, "Link has been shared", Snackbar.LENGTH_LONG).show()
        }
    }
}
