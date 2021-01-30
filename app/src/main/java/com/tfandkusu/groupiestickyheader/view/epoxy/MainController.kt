package com.tfandkusu.groupiestickyheader.view.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.tfandkusu.groupiestickyheader.data.DayWithMessages

class MainController : TypedEpoxyController<List<DayWithMessages>>() {
    override fun buildModels(days: List<DayWithMessages>) {
        days.forEach { dayWithMessages ->
            val day = dayWithMessages.day
            day {
                id("%04d%02d%02d".format(day.year, day.month, day.day))
                day(day)
            }
            dayWithMessages.messages.forEach {
                message {
                    id(it.id)
                    message(it)
                }
            }
        }
    }

    override fun isStickyHeader(position: Int): Boolean {
        return adapter.getModelAtPosition(position)::class == DayModel_::class
    }
}
