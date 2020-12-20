package com.tfandkusu.groupiestickyheader.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.databinding.ActivityMainBinding
import com.tfandkusu.groupiestickyheader.presenter.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

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
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.dayList.observe(this) { items ->
            var count = 0
            adapter.update(items.flatMap { dayWithMessages ->
                listOf(DateGroupieItem(dayWithMessages.time)) +
                        dayWithMessages.messages.map { message ->
                            ++count
                            MessageGroupieItem(message)
                        }
            })
            // Scroll to end position
            // In real product, this operation is done done for the first update.
            recyclerView.scrollToPosition(count - 1)
        }
    }
}
