package com.itis.secondcourseitis

import android.os.Build
import android.os.Bundle
import android.view.View.*
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.itis.secondcourseitis.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initListeners()


        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.transparant)
    }


    private fun initListeners() {
        with(binding) {
            btnEdit?.setOnClickListener {
                btnEdit.visibility = INVISIBLE
                btnEdit.isClickable = false
                btnAccept?.visibility = VISIBLE
                btnAccept?.isClickable = true

                tvUserName.visibility = INVISIBLE
                etUserName.text = etUserName.text
                etUserName.visibility = VISIBLE

                if (btnSaved != null) {
                    btnSaved.visibility = INVISIBLE
                }
            }
        }

        with(binding) {
            btnAccept?.setOnClickListener {
                btnAccept.visibility = INVISIBLE
                btnAccept.isClickable = false
                btnEdit?.visibility = VISIBLE
                btnEdit?.isClickable = true

                etUserName.visibility = GONE

                tvUserName.text = etUserName.text
                tvUserName.visibility = VISIBLE

                if (btnSaved != null) {
                    btnSaved.visibility = VISIBLE
                }
            }
        }
    }
}
