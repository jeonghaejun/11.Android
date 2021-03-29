package com.example.basic_1_edittext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        btnClose.setOnClickListener{
            val i = Intent()   // 리턴용 인텐트 생성
            i.putExtra(MainActivity.RESULT,txtMessage.text.toString())
            setResult(Activity.RESULT_OK, i)  // 리턴용 인텐트 등록
            finish()  // 액티비티 종료
        }
    }

    override fun onStart() {
        super.onStart()

        // 값이 없으면 리턴
        val i = intent ?: return  // 호출에 사용된 Intent
        val sID = i.getStringExtra(MainActivity.ID)
        val sPasswd = i.getStringExtra(MainActivity.PASSWD)

        txtMessage.text = "아이디: ${sID}\n패스워드: ${sPasswd}"
    }
}