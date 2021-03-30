package com.example.electframe

import android.Manifest
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mylib.MediaImage
import com.example.mylib.PermissionChecker
import kotlinx.android.synthetic.main.activity_photo_grid.*
import org.jetbrains.anko.startActivity

class PhotoGridActivity : AppCompatActivity() {
    companion object{
        val KEY_PHOTO_INDEX = "PHOTO_INDEX"
    }
    val permissionChecker by lazy {
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        PermissionChecker(this,permission)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_grid)

        if(permissionChecker.check()){
            init()
        }
    }

    private fun init() {
        val mediaImage = MediaImage(this)
//        recyclerView.adapter = PhotoAdapter(mediaImage.getAllPhotos(),::onItemClick)
        recyclerView.adapter = PhotoAdapter(mediaImage.getAllPhotos()){
            startActivity<MainActivity>(KEY_PHOTO_INDEX to it)
        }

        // 화면 모드가 가로인지 세로인지에 따라 운영할 컬럼수 조정
        if(resources.configuration.orientation==Configuration.ORIENTATION_PORTRAIT)
            recyclerView.layoutManager = GridLayoutManager(this,3)
        else
            recyclerView.layoutManager = GridLayoutManager(this,4)
    }

    fun onItemClick(pos:Int){
        startActivity<MainActivity>(KEY_PHOTO_INDEX to pos)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(permissionChecker.checkGranted(requestCode,permissions,grantResults)){
            init()
        }
    }
}