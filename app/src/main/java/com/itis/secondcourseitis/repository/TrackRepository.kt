package com.itis.secondcourseitis.repository

import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.model.Track

object TrackRepository {
    var id = 0;

    val tracks = arrayListOf(
        Track(id++, "Help!", "The Beatles", R.drawable.beatles_help, R.raw.help),
        Track(id++, "Come together", "The Beatles", R.drawable.come_together, R.raw.come_together),
        Track(id++, "Here comes the sun", "The Beatles", R.drawable.here_comes_the_sun, R.raw.here_comes_the_sun),
        Track(id++, "Hey jude", "The Beatles", R.drawable.hey_jude, R.raw.hey_jude),
        Track(id++, "Imagine", "John Lennon", R.drawable.imagine, R.raw.imagine_new),
        Track(id++, "Let it be", "The Beatles", R.drawable.let_it_be, R.raw.let_it_be),
        Track(id++, "All my loving", "The Beatles", R.drawable.all_my_loving, R.raw.all_my_loving),
    )
}
