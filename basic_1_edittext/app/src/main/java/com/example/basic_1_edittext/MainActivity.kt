package com.example.basic_1_edittext

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

// 인터페이스의 추상 메서드가 1개인 경우
class MyClickListener : View.OnClickListener{
    override fun onClick(v: View?) {
//        (v as TextView).text = xxx.name
    }
}

//fun onClick(v:View?){
//    (v as TextView).text = xxx.name
//}


class MainActivity : AppCompatActivity() {
//    var name = "Test"
    companion object{
        val REQUEST = 0
        val ID = "ID"
        val PASSWD = "PASSWD"
        val RESULT = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val myClickListener = MyClickListener()
//        edtName.setOnClickListener(myClickListener) // 아래와 같다(특정조건에서 추상메서드가 하나인 인터페이스에서)
//        edtName.setOnClickListener(::onClick)

//        edtName.setOnClickListener {
//            (it as TextView).text = name
//        }

//        edtName.setOnClickListener(object :View.OnClickListener){
//            override fun onClick(v: View?){
//                (v as TextView).text = name
//            }
//        })

        
        edtName.setOnFocusChangeListener() { v, hasFocus ->
            val edt = v as EditText
            val color = if (hasFocus) {
                Color.TRANSPARENT
            } else {
                Color.LTGRAY
            }
            edt.setBackgroundColor(color)
        }

//        edtPassWD.addTextChangedListener(MyWatcher())
        edtPassWD.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtViewPassWd.text = s

            }
        })

        btnLogin.setOnClickListener {
            val i = Intent(this, ResultActivity::class.java)
            i.putExtra(ID, edtName.text.toString())
            i.putExtra(PASSWD, edtPassWD.text.toString())

            startActivityForResult(i, REQUEST)
        }
    } // end of onCreate()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode != REQUEST) return

        data?.getStringExtra(RESULT).let{
            txtMessage.text = it
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}


//class MyWatcher : TextWatcher{
//    override fun afterTextChanged(s: Editable?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        TODO("Not yet implemented")
//    }
//
//}