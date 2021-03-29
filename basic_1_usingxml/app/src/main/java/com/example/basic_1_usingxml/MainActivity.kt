package com.example.basic_1_usingxml

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    val txt: TextView by lazy {findViewById<TextView>(R.id.txt_hello)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var txt = findViewById<TextView>(R.id.txt_hello)

        txt_hello.apply {
            text = "안녕하세요"
            textSize = 32.0F
            setTextColor(Color.parseColor("#FF0000"))
        }
    }
}