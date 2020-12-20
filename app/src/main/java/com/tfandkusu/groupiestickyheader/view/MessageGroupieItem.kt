package com.tfandkusu.groupiestickyheader.view

import android.view.View
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.data.Message
import com.tfandkusu.groupiestickyheader.databinding.ListItemMessageBinding
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

class MessageGroupieItem(private val message: Message) : BindableItem<ListItemMessageBinding>() {

    private val sdf = SimpleDateFormat("HH:mm", Locale.JAPAN)

    override fun bind(viewBinding: ListItemMessageBinding, position: Int) {
        viewBinding.icon.setImageResource(message.iconResId)
        viewBinding.name.text = message.name
        viewBinding.time.text = sdf.format(Date(message.time))
        viewBinding.body.text = message.body
    }

    override fun getLayout() = R.layout.list_item_message

    override fun initializeViewBinding(view: View): ListItemMessageBinding {
        return ListItemMessageBinding.bind(view)
    }
    
    override fun isSameAs(other: Item<*>): Boolean {
        return if (other is MessageGroupieItem) {
            message.id == other.message.id
        } else {
            false
        }
    }

    override fun hasSameContentAs(other: Item<*>): Boolean {
        return if (other is MessageGroupieItem) {
            message == other.message
        } else {
            false
        }
    }
}
