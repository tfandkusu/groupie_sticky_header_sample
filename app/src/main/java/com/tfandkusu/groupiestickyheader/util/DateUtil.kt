package com.tfandkusu.groupiestickyheader.util

import com.tfandkusu.groupiestickyheader.data.YMD
import java.util.*

/**
 * Extract year,month and day from Unix time
 */
fun extractYMD(time: Long): YMD {
    val calendar = GregorianCalendar.getInstance()
    calendar.timeInMillis = time
    return YMD(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}

