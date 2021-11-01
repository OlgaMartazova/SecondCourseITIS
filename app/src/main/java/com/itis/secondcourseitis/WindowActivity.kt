package com.itis.secondcourseitis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.itis.secondcourseitis.databinding.ActivityWindowBinding

class WindowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWindowBinding
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWindowBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        id = intent?.extras?.getInt("ID")

        with(binding) {
            //как тут не ставить !! я не знаю((
            Glide.with(this.root).load(WindowRepository.windows[id!!].url).into(ivPromo)
            tvName.text = "NAME: ${WindowRepository.windows[id!!].title}"
            tvDesc.text = "DESCRIPTION: ${WindowRepository.windows[id!!].form}"
        }
    }
}
