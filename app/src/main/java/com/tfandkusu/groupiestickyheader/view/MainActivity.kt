package com.tfandkusu.groupiestickyheader.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tfandkusu.groupiestickyheader.R
import com.tfandkusu.groupiestickyheader.databinding.ActivityMainBinding
import com.tfandkusu.groupiestickyheader.presenter.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel.item.observe(this) { items ->
            items.map {
                Log.d("MainActivity", it.toString())
            }
        }
    }
}
