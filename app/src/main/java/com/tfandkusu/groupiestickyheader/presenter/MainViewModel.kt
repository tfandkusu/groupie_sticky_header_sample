package com.tfandkusu.groupiestickyheader.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mooveit.library.Fakeit
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.data.DayWithMessages
import com.tfandkusu.groupiestickyheader.data.Message
import com.tfandkusu.groupiestickyheader.data.YMD
import com.tfandkusu.groupiestickyheader.util.extractYMD
import java.util.*
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _dayList = MutableLiveData(listOf<DayWithMessages>())

    val dayList: LiveData<List<DayWithMessages>> = _dayList

    init {
        val messages = makeMessages()
        val days = separateByDayAndMessages(messages)
        this._dayList.value = days
    }

    private fun makeMessages(): List<Message> {
        Fakeit.init()
        // Make Mocking Data
        data class NameAndIcon(val name: String, val iconResId: Int)
        // Make member list
        val names = (0 until 4).map {
            Fakeit.name().lastName()
        }.toMutableList()
        // Icon images
        // https://www.irasutoya.com/
        val iconResIds = listOf(
            R.drawable.cat_mikeneko2,
            R.drawable.animal_alpaca_huacaya,
            R.drawable.animal_wallaby_kangaroo,
            R.drawable.animal_pony
        )
        val nameAndIcons = names.mapIndexed { index, name ->
            NameAndIcon(name, iconResIds[index])
        }.toMutableList()
        // Make morning messages.
        val morning = mutableListOf("Good morning.", "Mornin", "Start working", "I do the work")
        // Lunch messages.
        val lunches =
            mutableListOf("Lunch", "Lunch break", "Have lunch break", "Luncheon recess")
        // leave work messages.
        val leaves =
            mutableListOf("Leave work", "Finish work", "Thank you for your support", "End of work")
        //
        val c = GregorianCalendar.getInstance()
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.MILLISECOND, 0)
        var messages = mutableListOf<Message>()
        (0 until 10).map {
            if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                nameAndIcons.shuffle()
                morning.shuffle()
                lunches.shuffle()
                leaves.shuffle()
                names.indices.map {
                    // Morning Message
                    c.set(Calendar.HOUR_OF_DAY, 9)
                    c.set(Calendar.MINUTE, 50 + Random.nextInt(11))
                    messages.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].iconResId,
                            c.timeInMillis,
                            morning[it]
                        )
                    )
                    // Launch Message
                    c.set(Calendar.HOUR_OF_DAY, 12)
                    c.set(Calendar.MINUTE, Random.nextInt(120))
                    messages.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].iconResId,
                            c.timeInMillis,
                            lunches[it]
                        )
                    )
                    // Leave message
                    c.set(Calendar.HOUR_OF_DAY, 19)
                    c.set(Calendar.MINUTE, Random.nextInt(30))
                    messages.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].iconResId,
                            c.timeInMillis,
                            leaves[it]
                        )
                    )
                }
            }
            c.add(Calendar.DAY_OF_MONTH, -1)
        }
        // Sort
        messages.sortBy {
            it.time
        }
        // set ids
        messages = messages.mapIndexed { index, message ->
            message.copy(id = (index + 1).toLong())
        }.toMutableList()
        return messages
    }

    private fun separateByDayAndMessages(messages: List<Message>): List<DayWithMessages> {
        val days = mutableListOf<DayWithMessages>()
        var previousDay: YMD? = null
        messages.map {
            val md = extractYMD(it.time)
            if (md != previousDay) {
                days.add(DayWithMessages(md, listOf(it)))
                previousDay = md
            } else {
                days.lastOrNull()?.messages?.let { messages ->
                    days[days.size - 1] = days[days.size - 1].copy(messages = messages + it)
                }
            }
        }
        return days
    }
}
