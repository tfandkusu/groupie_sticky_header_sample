package com.tfandkusu.groupiestickyheader.view

import android.view.View
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.data.YMD
import com.tfandkusu.groupiestickyheader.databinding.ListItemDayBinding
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

class DayGroupieItem(private val day: YMD) : BindableItem<ListItemDayBinding>() {
    private val sdf = SimpleDateFormat("MM月dd日（EEE）", Locale.JAPAN)

    override fun bind(viewBinding: ListItemDayBinding, position: Int) {
        // javaのDateに変換してからSimpleDateFormatクラスを使って文字列にする。
        viewBinding.day.text = sdf.format(day.toDate())
    }

    override fun getLayout() = R.layout.list_item_day

    override fun initializeViewBinding(view: View): ListItemDayBinding {
        return ListItemDayBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean {
        return if (other is DayGroupieItem) {
            day == other.day
        } else {
            false
        }
    }

    override fun hasSameContentAs(other: Item<*>): Boolean {
        return if (other is DayGroupieItem) {
            day == other.day
        } else {
            false
        }
    }
}
