package com.example.mqtt_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.example.mylib.net.Mqtt
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage

const val SUB_TOPIC = "iot/#"
const val PUB_TOPIC = "iot/led"
const val SERVER_URI = "tcp://192.168.0.4:1883"

class MainActivity : AppCompatActivity() {
    val TAG = "MqttActivity"
    lateinit var mqttClient: Mqtt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mqttClient = Mqtt(this, SERVER_URI)
        try {
            // mqttClient.setCallback { topic, message ->}
            mqttClient.setCallback(::onReceived)
            mqttClient.connect(arrayOf<String>(SUB_TOPIC))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        ledSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            val value = if(isChecked) "on" else "off"
            mqttClient.publish("iot/control/led",value)
        }

        seekAngle.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mqttClient.publish("iot/control/servo",progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    fun onReceived(topic: String, message: MqttMessage) {
    // 토픽 수신 처리
        val msg = String(message.payload)
        Log.i(TAG,"$topic : $msg")

        //어느 스레드가 onReceived를 실행하는가 확인..
        // MqttService에서 runOnUIThread()로 호출
        txtWeatherInfo.text="$topic : $msg"
    }
    fun publish() {
        mqttClient.publish(PUB_TOPIC, "1")
    }
}