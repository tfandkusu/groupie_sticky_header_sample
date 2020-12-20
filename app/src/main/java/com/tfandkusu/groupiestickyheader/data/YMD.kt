package com.tfandkusu.groupiestickyheader.data

import java.util.*

data class YMD(val year: Int, val month: Int, val day: Int) {
    fun toDate(): Date {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = 0L
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }
}
