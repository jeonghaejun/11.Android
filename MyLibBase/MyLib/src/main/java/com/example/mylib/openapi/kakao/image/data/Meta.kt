package com.example.mylib.openapi.kakao.image.data

data class Meta(
    val is_end: Boolean,       // 현재 페이지가 마지막 페이지인지 여부
    val pageable_count: Int,   // total_count 중에 노출가능 문서수
    val total_count: Int       // 검색어에 검색된 문서수
)