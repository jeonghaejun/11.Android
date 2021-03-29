package com.example.uidesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // 부모의 onCreate() 호출
        setContentView(R.layout.activity_main3)  // 화면을 구성하는 메서드.
    }
}