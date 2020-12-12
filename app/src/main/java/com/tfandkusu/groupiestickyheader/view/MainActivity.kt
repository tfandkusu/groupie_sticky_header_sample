package com.tfandkusu.groupiestickyheader.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    }
}
