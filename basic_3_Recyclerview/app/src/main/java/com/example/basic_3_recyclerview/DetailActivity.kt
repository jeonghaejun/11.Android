package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getSerializableExtra("DATA") as MainData
        Log.d(TAG,data.toString())
    }
}

// 목록 --> 아이템 선택 --> 상세보기
// 뒤로가기 --> 목록 --> 아이템 선택 --> 상세보기
// 좌우 슬라이딩
