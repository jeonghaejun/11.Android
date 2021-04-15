package com.example.gateway_project

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.example.mylib.Notification
import com.example.mylib.net.Mqtt
import com.example.mylib.openapi.piapi.PiApi
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage

const val SUB_TOPIC = "iot/#"
const val PUB_TOPIC = "iot/led"
const val SERVER_URI = "tcp://192.168.0.4:1883"

class MainActivity : AppCompatActivity() {

    companion object{
        const val CHANNEL_ID = "com.example.gateway_project"
        const val CHANNEL_NAME = "My Channel"
        const val CHANNEL_DESCRIPTION = "Channel Test"
        const val NOTIFICATION_REQUEST = 0
        const val NOTIFICATION_ID = 100
    }

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

            if (isChecked){ // switch on
                PiApi.controlLed("1","on"){
                    if(it.result=="OK"){
                        imgLight.imageTintList = ColorStateList.valueOf(Color.YELLOW)
                    }
                }
            } else {  // switch off
                PiApi.controlLed("1","off"){
                    if (it.result=="OK"){
                        imgLight.imageTintList = ColorStateList.valueOf(Color.DKGRAY)
                    }
                }
            }
        }

        seekAngle2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mqttClient.publish("iot/control/servo",progress.toString())
                PiApi.controlServo("1",progress){
                    if(it.result == "OK"){
                        txtServo2.text = "서보 모터 : ${progress}°"
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    fun onReceived(topic: String, message: MqttMessage) {

        val msg = String(message.payload)
        Log.i(TAG,"$topic : $msg")

        txtWeatherInfo.text="$topic : $msg"
        if(msg=="on"){PIR_img.imageTintList = ColorStateList.valueOf(Color.RED)

            val noti = Notification(this)
            noti.createNotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_DESCRIPTION)
            val pendingIntent = noti.getPendingIntent(
                    // 눌럿을때 나오는 창바꾸기
                     DetectionActivity::class.java, NOTIFICATION_REQUEST)
//            this.javaClass, NOTIFICATION_REQUEST)
            noti.notifyBasic(CHANNEL_ID, NOTIFICATION_ID,
                    "Alarm","침입 발생",
                    R.drawable.ic_launcher_foreground,pendingIntent)
        }
        else{PIR_img.imageTintList = ColorStateList.valueOf(Color.DKGRAY)}
    }
    fun publish() {
        mqttClient.publish(PUB_TOPIC, "1")
    }
}
