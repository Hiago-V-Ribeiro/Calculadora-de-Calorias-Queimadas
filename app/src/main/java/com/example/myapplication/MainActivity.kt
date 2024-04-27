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


class MainActivity: AppCompatActivity() {
    val peso = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TextNome = findViewById<EditText>(R.id.TextNome)
        val TextAltura = findViewById<EditText>(R.id.TextAltura)
        val TextPeso = findViewById<EditText>(R.id.TextPeso)
        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)





        buttonEnviar.setOnClickListener {
            val nome = TextNome.text.toString()
            val altura = TextAltura.text.toString().toFloatOrNull()
            val peso = TextPeso.text.toString().toFloatOrNull()

            val message = "Nome: $nome\nAltura: $altura\nPeso: $peso"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            val intent = Intent(this, Contagem::class.java)
            startActivity(intent)
        }

    }


}

