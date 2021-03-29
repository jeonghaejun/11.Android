package com.example.basic_2_logcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var nCount : Int = 0
    val nMaxCount : Int = 3
    val TAG = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogcatTest.setOnClickListener{
            Log.d(TAG, "${nCount++} clicked")
            try{
                val nResult = nMaxCount / (nMaxCount - nCount)
                Log.d(TAG, "nMaxCount / (nMaxCount - nCount) is ${nResult} ")
            } catch ( e : Exception ) {
                Log.e(TAG, "${nCount} : ${e.toString()}")
            }
        }
    }
}