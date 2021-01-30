package com.tfandkusu.groupiestickyheader.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.databinding.ActivityMainBinding
import com.tfandkusu.groupiestickyheader.presenter.MainViewModel
import com.tfandkusu.groupiestickyheader.view.epoxy.MainController
import com.tfandkusu.groupiestickyheader.view.epoxy.StickyHeaderLinearLayoutManager

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
        val controller = MainController()
        recyclerView.adapter = controller.adapter
        recyclerView.layoutManager = StickyHeaderLinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        viewModel.dayList.observe(this) { dayList ->
            controller.setData(dayList)
            // Scroll to end position
            // In real product, this operation is done done for the first update.
            val count = dayList.sumBy { 1 + it.messages.size }
            recyclerView.scrollToPosition(count - 1)
        }
    }
}
