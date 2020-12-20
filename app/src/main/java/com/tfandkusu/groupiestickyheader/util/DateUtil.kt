package com.tfandkusu.groupiestickyheader.util

import java.util.*

data class MonthDay(val month: Int, val day: Int)

fun extractMonthDay(time: Long): MonthDay {
    val calendar = GregorianCalendar.getInstance()
    calendar.timeInMillis = time
    return MonthDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
}
