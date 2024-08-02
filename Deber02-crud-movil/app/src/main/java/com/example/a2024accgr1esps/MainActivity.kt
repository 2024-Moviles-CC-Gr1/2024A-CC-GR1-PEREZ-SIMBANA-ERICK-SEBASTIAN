package com.example.a2024accgr1esps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar Base de datos Hospital
        EBaseDeDatos.tablaFactura = ESqliteHelperFactura(
            this
        )

        //Inicializar Base de datos Hospital
        EBaseDeDatos.tablaDetalleFactura = ESqliteHelperDetalleFactura(
            this
        )

        val botonAbrirFactura = findViewById<Button>(R.id.btn_open_factura)
        botonAbrirFactura.setOnClickListener {
            val intent = Intent(this, ECrudFactura::class.java)
            startActivity(intent)
        }

        val botonAbrirDetalleFactura = findViewById<Button>(R.id.btn_open_detalle_factura)
        botonAbrirDetalleFactura.setOnClickListener {
            val intent = Intent(this, ECrudDetalleFactura::class.java)
            startActivity(intent)
        }
    }
}
