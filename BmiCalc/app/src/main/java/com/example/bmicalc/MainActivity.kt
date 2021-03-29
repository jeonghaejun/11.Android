package com.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    companion object{
        val KEY_HEIGHT = "KEY_HEIGHT"
        val KEY_WEIGHT = "KEY_WEIGHT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener {
            val height = heightEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toInt()

            saveData(height, weight)

            startActivity<ResultActivity>(
                KEY_HEIGHT to height,
                KEY_WEIGHT to weight
            )
        }
    }
    // 데이터 저장하고 복원하기
    private  fun saveData(height: Int, weight: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt(KEY_HEIGHT, height)
            .putInt(KEY_WEIGHT, weight)
            .apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt(KEY_HEIGHT,0)
        val weight = pref.getInt(KEY_WEIGHT,0)

        if (height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

}