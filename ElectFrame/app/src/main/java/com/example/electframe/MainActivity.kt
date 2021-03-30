package com.example.electframe

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylib.MediaImage
import com.example.mylib.PermissionChecker
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaImage = MediaImage(this)

        val adapter = PhotoPagerAdapter(this,mediaImage.getAllPhotos())
        viewPager.adapter = adapter

        val pos = intent.getIntExtra(PhotoGridActivity.KEY_PHOTO_INDEX,0)
        viewPager.adapter = adapter

        // 3초마다 자동으로 슬라이드
        timer(period = 3000){
            runOnUiThread {
                if(viewPager.currentItem < adapter.itemCount -1){
                    viewPager.currentItem++
                } else {
                    viewPager.currentItem = 0
                }
            }
        }
    }
}
