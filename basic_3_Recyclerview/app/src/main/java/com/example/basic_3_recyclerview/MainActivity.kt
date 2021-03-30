package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    val items= mutableListOf<MainData>()
    init{
        for(i in 1..70){
            items+=MainData("Titele$i","Content$i")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main_list.adapter = MainAdapter(items,::onItemClick)
        rv_main_list.layoutManager = LinearLayoutManager(this)
//        rv_main_list.layoutManager = GridLayoutManager(this,2)

        btnAdd.setOnClickListener {
            val data = MainData(txtTitle.text.toString(),txtContent.text.toString())
            items.add(0,data)  // 리스트의 첫번째 위치에 추가
            longToast(data.toString())
            // List가 수정된 사실을 adapter는 모름 --> 자동으로 갱신되지 않음
            // adapter에게 데이터가 수정된 사실을 통지하고, 화면 갱신을 유도해야 함
            rv_main_list.adapter?.notifyDataSetChanged()
        }
    }

    fun onItemClick(pos: Int){
        val data = items[pos]
//        toast(data.toString())

        // 무슨일을 할까?
        // 새로운 액티비티를 이용해서 상세보기로 전환...
        startActivity<DetailActivity>("DATA" to data)
    }
}