package com.example.basic_2_activitylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName // 패키지명을 제외한 클래스 이름
    var nLineNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"${nLineNumber++}: onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"${nLineNumber++}: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"${nLineNumber++}: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"${nLineNumber++}: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"${nLineNumber++}: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"${nLineNumber++}: onDestroy")
    }

}