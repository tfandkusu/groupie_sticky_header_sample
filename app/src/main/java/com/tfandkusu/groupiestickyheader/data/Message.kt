package com.tfandkusu.groupiestickyheader.data

import androidx.annotation.DrawableRes

data class Message(
    val id: Long, val name: String, @DrawableRes val iconResId: Int, val time: Long,
    val body: String
)
