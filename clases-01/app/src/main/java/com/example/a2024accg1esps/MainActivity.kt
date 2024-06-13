package com.example.a2024accg1esps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonCicloVida
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
    }

    fun irActividad(
        clase: Class<*>,
    ){
        val intend = Intent(this, clase)
        startActivity(intend)
    }
}