package com.tfandkusu.groupiestickyheader.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.databinding.ActivityMainBinding
import com.tfandkusu.groupiestickyheader.presenter.MainViewModel
import io.doist.recyclerviewext.sticky_headers.StickyHeadersLinearLayoutManager

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpRecyclerView(binding.recyclerView)
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val adapter = StickyHeaderGroupAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StickyHeadersLinearLayoutManager<StickyHeaderGroupAdapter>(this)
        recyclerView.setHasFixedSize(true)
        viewModel.dayList.observe(this) { dayList ->
            val items = dayList.flatMap { dayWithMessages ->
                listOf(DayGroupieItem(dayWithMessages.ymd)) +
                        dayWithMessages.messages.map { message ->
                            MessageGroupieItem(message)
                        }
            }
            adapter.update(items)
            // Scroll to end position
            // In real product, this operation is done done for the first update.
            recyclerView.scrollToPosition(items.size - 1)
        }
    }
}
