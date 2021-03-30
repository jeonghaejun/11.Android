package com.example.basic_3_recyclerview

import java.io.Serializable
// 생성자 호출여부에 따라 클래스인지 인터페이스인지 구별
data class MainData(val title:String, val content:String) : Serializable // 인터페이스는 이름만
