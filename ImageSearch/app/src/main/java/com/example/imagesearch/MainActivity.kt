package com.example.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mylib.openapi.kakao.image.KakaoImageSerach
import com.example.mylib.openapi.kakao.image.data.Document
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val imageList = mutableListOf<Document>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageListView.adapter = ImageListAdapter(imageList, :: onItemClick)
        imageListView.layoutManager = GridLayoutManager(this, 3)

        btnSearch.setOnClickListener {
            val keyword = editKeyword.text.toString()
            searchImage(keyword)
            editKeyword.setText("")
        }
    }

    fun onItemClick(doc: Document){

    }

    private fun searchImage(keyword: String) {
        KakaoImageSerach.searchImage(keyword,1){
            imageList.addAll(it.documents)
            imageListView.adapter?.notifyDataSetChanged()
        }
    }
}