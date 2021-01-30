package com.tfandkusu.groupiestickyheader.view.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.data.Message
import java.text.SimpleDateFormat
import java.util.*

@EpoxyModelClass(layout = R.layout.list_item_message)
abstract class MessageModel : EpoxyModelWithHolder<MessageModel.Holder>() {
    private val sdf = SimpleDateFormat("HH:mm", Locale.JAPAN)

    @EpoxyAttribute
    lateinit var message: Message

    override fun bind(holder: MessageModel.Holder) {
        holder.icon.setImageResource(message.iconResId)
        holder.name.text = message.name
        holder.time.text = sdf.format(Date(message.time))
        holder.body.text = message.body
    }

    class Holder : EpoxyHolder() {

        lateinit var icon: ImageView

        lateinit var name: TextView

        lateinit var time: TextView

        lateinit var body: TextView

        override fun bindView(itemView: View) {
            icon = itemView.findViewById(R.id.icon)
            name = itemView.findViewById(R.id.name)
            time = itemView.findViewById(R.id.time)
            body = itemView.findViewById(R.id.body)
        }
    }
}
