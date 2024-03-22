package com.example.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MainViewModel: ViewModel() {
    companion object {
        private const val ONE_SECOND = 1000
    }

    private val mInitialTime = SystemClock.elapsedRealtime()


    //object live data
   // MutableLiveData bisa kita ubah value-nya, sedangkan LiveData bersifat Read-Only.
    private val mElapsedTime =MutableLiveData<Long?>()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object :TimerTask(){
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }

        }, ONE_SECOND.toLong() , ONE_SECOND.toLong())
    }

    fun getElapseTime() : LiveData<Long?>{
        return mElapsedTime
    }
}