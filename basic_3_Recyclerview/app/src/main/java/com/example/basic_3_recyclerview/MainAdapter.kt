package com.example.basic_3_recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(val items: List<MainData>,val onItemClick:(Int)->Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder> () {
    val TAG = javaClass.simpleName

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.tv_main_title  // title 데이터를 출력할 View의 참조
        val tvContent = itemView.tv_main_content // content 데이터를 출력할 View의 참조

        init{
            itemView.setOnClickListener{
                val pos = adapterPosition  // 현재 ViewHolder가 몇번째 index인지 알 수 있는 속성
                if (pos != RecyclerView.NO_POSITION) {
                    Log.d(TAG, "Item Clicked!! - $pos")
                    // Activity의 onClickItem() 호출
                    onItemClick(pos)
                }
            }
        }
    }

    // item 하나를 관리한 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main,parent,false)
        return  MainViewHolder(view)
    }

    // 데이터(item)의 개수 리턴
    override fun getItemCount() = items.size

    // 표시할 데이터와 View 컴포넌트 연결
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items[position].let {
            with(holder){ // with --> apply + lat
                tvTitle.text = it.title
                tvContent.text = it.content
            }

//            holder.apply {  <-- with 대신 이거해도 된다 이번예제는
//                tvTitle.text = it.title
//                tvContent.text = it.content
//            }
        }
    }
}