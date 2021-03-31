package com.example.mylib.openapi.kakao.image.data

data class Document(
    val collection: String,        // 컬렉션
    val datetime: String,          // [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz],
    val display_sitename: String,  // 출처명
    val doc_url: String,           // 문서 URL
    val height: Int,               // 이미지의 세로 크기
    val image_url: String,         // 이미지의 URL
    val thumbnail_url: String,     // 이미지 썸네일 URL
    val width: Int                 // 이미지의 가로 크기
)