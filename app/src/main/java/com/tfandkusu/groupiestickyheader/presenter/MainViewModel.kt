package com.tfandkusu.groupiestickyheader.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mooveit.library.Fakeit
import com.tfandkusu.groupiestickyheader.data.Message
import java.util.*
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _items = MutableLiveData(listOf<Message>())

    val item: LiveData<List<Message>> = _items

    init {
        Fakeit.init()
        // Make Mocking Data
        data class NameAndIcon(val name: String, val icon: String)
        // Make member list
        val names = (0 until 4).map {
            Fakeit.name().lastName()
        }.toMutableList()
        // Icon images
        // https://www.irasutoya.com/
        val iconImages = listOf(
            "https://3.bp.blogspot.com/-X6ruoCYjbMQ/WSa8zi2McUI/AAAAAAABEhc/OtxhrQP4PYIusK-uT61_NHbxUmlEbLWgACLcB/s800/cat_mikeneko2.png",
            "https://1.bp.blogspot.com/-PNtqP_kYbEw/XuMM_ImLmVI/AAAAAAABZeo/7aJRwvd66KEEIsNzjP3ddRxz_DsAWJlZQCNcBGAsYHQ/s1600/animal_alpaca_huacaya.png",
            "https://1.bp.blogspot.com/-c-OqcO_Duvo/XxU0Tix8y8I/AAAAAAABaJA/WBNfRQv9hfgAqOpnqBbvyFXUVp0i04LJQCNcBGAsYHQ/s1600/animal_wallaby_kangaroo.png",
            "https://2.bp.blogspot.com/-rRp0ZRxF3y0/U-8FlTBHG4I/AAAAAAAAkuU/lDng9w4s2Ig/s800/animal_pony.png"
        )
        val nameAndIcons = names.mapIndexed { index, name ->
            NameAndIcon(name, iconImages[index])
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
        val items = mutableListOf<Message>()
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
                    items.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].icon,
                            c.timeInMillis,
                            morning[it]
                        )
                    )
                    // Launch Message
                    c.set(Calendar.HOUR_OF_DAY, 12)
                    c.set(Calendar.MINUTE, Random.nextInt(120))
                    items.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].icon,
                            c.timeInMillis,
                            lunches[it]
                        )
                    )
                    // Leave message
                    c.set(Calendar.HOUR_OF_DAY, 19)
                    c.set(Calendar.MINUTE, Random.nextInt(30))
                    items.add(
                        Message(
                            0,
                            nameAndIcons[it].name,
                            nameAndIcons[it].icon,
                            c.timeInMillis,
                            leaves[it]
                        )
                    )
                }
            }
            c.add(Calendar.DAY_OF_MONTH, -1)
        }
        // Sort
        items.sortBy {
            it.time
        }
        // Set ids and save to LiveData
        this._items.value = items.mapIndexed { index, message ->
            message.copy(id = (index + 1).toLong())
        }
    }

}
