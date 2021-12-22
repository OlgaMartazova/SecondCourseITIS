package com.itis.secondcourseitis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.itis.secondcourseitis.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private var windowAdapter: WindowAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        windowAdapter = WindowAdapter(WindowRepository.windows, Glide.with(this)) {
            showSelectedTitle(it)
        }
        binding.rvWindows.run {
            adapter = windowAdapter
        }
    }
    private fun showSelectedTitle(id: Int) {
        startActivity(Intent(this,
                                WindowActivity::class.java
                            )
                            .apply {
                                putExtra("ID", id)
                            })
        //Snackbar.make(binding.root, "Title: ${WindowRepository.windows[id]}", Snackbar.LENGTH_LONG).show()
    }
}
