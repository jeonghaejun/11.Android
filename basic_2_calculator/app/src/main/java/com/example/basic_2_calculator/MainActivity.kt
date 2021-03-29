package com.example.basic_2_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 빼기 함수(메소드)
    private fun subNumber(i: Int, i1: Int) : Int{
        return i - i1;
    }
    // 더하기 함수(메소드)
    private fun addNumber(i: Int, i1: Int) : Int{
        return i + i1;
    }
    // 함수형 예제를 위한 계산함수
    private fun calculate(pFunc : (Int, Int)->Int,
                          num1 : Int, num2 : Int) : Int {
        return pFunc(num1, num2)
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        btnPlus.setOnClickListener{ // + 버튼을 눌렀을 때(일반적인 스타일)
//            // 입력받은 값이 null인지 채크
//            if (firstNumber.text == null || secondNumber.text == null) {
//                return@setOnClickListener  // 람다 함수 리턴 방법
//            }
//            // 입력받은 값이 ""인지 채크
//            if (firstNumber.text.length == 0 || secondNumber.text.length == 0) {
//                return@setOnClickListener
//            }
//            // 숫자값을 가져오기
//            // EditText의 text타입은 CharSequence타입
//            var first = firstNumber.text.toString()
//            var second = secondNumber.text.toString()
//            // 문자열을 숫자로 변형하는 방법 : toInt()
//            var result = addNumber(first.toInt(), second.toInt())
//            txtResult.setText("$result")
//        }
//
//        // - 버튼을 눌렀을 때(함수형 프로그래밍 스타일)
//        btnMinus.setOnClickListener {
//            // 입력받은 값을 검증
//            val lstCheck = listOf(firstNumber, secondNumber)
//            lstCheck
//                .map { if (it == null) return@setOnClickListener else it }
//                .map { if (it.text.length < 1) return@setOnClickListener else it}
//            // 숫자값을 가져오기
//            val lstNumber = lstCheck.map { it.text.toString().toInt() }
//            lstNumber.let {
//                calculate(::subNumber,
//                    it.get(0), it.get(1))
//                    .let { txtResult.text = "${it}" }
//            }
//        }
//    }



// 위의과정의 코드 중복을 없애기 위해 함수를 만들었다.
    private fun calculate(pFunc: (Int, Int) -> Int){
        // 입력받은 값이 null인지 채크
        if (firstNumber.text == null || secondNumber.text == null) {
            return
        }
        // 입력받은 값이 ""인지 채크
        if (firstNumber.text.length == 0 || secondNumber.text.length == 0) {
            return
        }
        // 숫자값을 가져오기
        // EditText의 text타입은 CharSequence타입
        var first = firstNumber.text.toString()
        var second = secondNumber.text.toString()

        // 문자열을 숫자로 변형하는 방법 : toInt()
        var result = pFunc(first.toInt(), second.toInt())
        txtResult.setText("$result")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlus.setOnClickListener{ // + 버튼을 눌렀을 때(일반적인 스타일)
            calculate(::addNumber)
        }

        // - 버튼을 눌렀을 때(함수형 프로그래밍 스타일)
        btnMinus.setOnClickListener {
            calculate(::subNumber)
        }
    }
}