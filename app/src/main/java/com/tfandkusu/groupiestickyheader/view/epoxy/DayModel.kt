package com.tfandkusu.groupiestickyheader.view.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.data.YMD
import java.text.SimpleDateFormat
import java.util.*

@EpoxyModelClass(layout = R.layout.list_item_day)
abstract class DayModel : EpoxyModelWithHolder<DayModel.Holder>() {

    private val sdf = SimpleDateFormat("MM月dd日（EEE）", Locale.JAPAN)

    @EpoxyAttribute
    lateinit var day: YMD

    override fun bind(holder: Holder) {
        val text = sdf.format(day.toDate())
        holder.day.text = text
        holder.root.tag = text
    }

    class Holder : EpoxyHolder() {

        lateinit var root: View

        lateinit var day: TextView

        override fun bindView(itemView: View) {
            root = itemView.rootView
            day = itemView.findViewById(R.id.day)
        }
    }
}
