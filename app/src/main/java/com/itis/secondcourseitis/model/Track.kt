package com.itis.secondcourseitis.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Track (
    val id: Int,
    val title: String,
    val author:String,
    @DrawableRes val cover:Int,
    @RawRes val song:Int
)
