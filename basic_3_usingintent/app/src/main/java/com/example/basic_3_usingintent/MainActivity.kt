package com.example.basic_3_usingintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    private fun setUpUI(){
        // 암묵적 인텐트 호출
        btnSMS.setOnClickListener{
            // SMS 보내기
            val uri = Uri.parse("smsto:"+"010-1234-1234")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "사랑해 안드로이드 앱으로 보내는거얌 ㅎㅎ")
            startActivity(intent)
        }

        btnMail.setOnClickListener{
            // Email 보내기
            val uri = Uri.parse("mailto:"+"qwer@naver.com")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra(Intent.EXTRA_EMAIL,"qwer@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT,"테스트")
            startActivity(intent)
        }

        btnInternet.setOnClickListener{
            // 인터넷 이동동
            //val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 114))
            //startActivity(intent)
            val uri = Uri.parse("http://vintageappmaker.tumblr.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMap.setOnClickListener{
            // 지도이동
            val uri = Uri.parse("geo: 37.5310091, 127.0261659")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMarket.setOnClickListener{
            // 마켓으로 이동하기
            val uri = Uri.parse("market://details?id=com.psw.moringcall")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}