package com.tfandkusu.groupiestickyheader.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _progress = MutableLiveData(false)

    private val progress: LiveData<Boolean> = _progress

}
