package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Contagem: AppCompatActivity(), SensorEventListener {
    var gyroscopeSensor: Sensor? = null
    lateinit var sensorManager: SensorManager
    lateinit var stepCountTextView: TextView
    lateinit var caloriastext: TextView
    var stepCount = 0
    var caloriasq = 0.0
    val peso = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contagem)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        stepCountTextView = findViewById(R.id.stepCountTextView)
        caloriastext = findViewById(R.id.caloriastext)

    }

    override fun onResume() {
        super.onResume()
        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val magnitude = Math.sqrt((x * x + y * y + z * z).toDouble())

            val threshold = 2.0

            if (magnitude > threshold) {
                stepCount++
                stepCountTextView.text = "Passos: $stepCount"
            }

            caloriasq = ( peso * stepCount * 0.0175)
            caloriastext.text = "Calorias Perdidas: $stepCount"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Se a precisão do sensor mudar, este método será chamado.
    }
}
