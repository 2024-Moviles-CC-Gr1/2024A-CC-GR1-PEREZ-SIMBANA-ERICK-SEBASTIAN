package com.example.a2024accgr1esps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudFactura : AppCompatActivity() {
    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_sqlite_factura),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_factura)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd_factura)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_factura)
            val cliente = findViewById<EditText>(R.id.input_cliente)
            val fecha = findViewById<EditText>(R.id.input_fecha)
            val descuento = findViewById<EditText>(R.id.input_descuento)
            val pagada = findViewById<EditText>(R.id.input_pagada)
            val factura = EBaseDeDatos.tablaFactura!!.consultarFacturaPorID(id.text.toString().toInt())
            if (factura == null) {
                mostrarSnackbar("Factura no encontrada")
                id.setText("")
                cliente.setText("")
                fecha.setText("")
                descuento.setText("")
                pagada.setText("")
            } else {
                id.setText(factura.id.toString())
                cliente.setText(factura.cliente)
                fecha.setText(factura.fecha)
                descuento.setText(factura.descuento.toString())
                pagada.setText(if (factura.pagada) "Sí" else "No")
                mostrarSnackbar("Factura encontrada")
            }
        }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd_factura)
        botonCrearBDD.setOnClickListener {
            val cliente = findViewById<EditText>(R.id.input_cliente)
            val fecha = findViewById<EditText>(R.id.input_fecha)
            val descuento = findViewById<EditText>(R.id.input_descuento)
            val pagada = findViewById<EditText>(R.id.input_pagada)
            val respuesta = EBaseDeDatos.tablaFactura!!.createFactura(
                cliente.text.toString(),
                fecha.text.toString(),
                descuento.text.toString().toDouble(),
                pagada.text.toString().equals("Sí", true)
            )
            if (respuesta) mostrarSnackbar("Factura creada")
        }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd_factura)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_factura)
            val cliente = findViewById<EditText>(R.id.input_cliente)
            val fecha = findViewById<EditText>(R.id.input_fecha)
            val descuento = findViewById<EditText>(R.id.input_descuento)
            val pagada = findViewById<EditText>(R.id.input_pagada)
            val respuesta = EBaseDeDatos.tablaFactura!!.actualizarFactura(
                id.text.toString().toInt(),
                cliente.text.toString(),
                fecha.text.toString(),
                descuento.text.toString().toDouble(),
                pagada.text.toString().equals("Sí", true)
            )
            if (respuesta) mostrarSnackbar("Factura actualizada")
        }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd_factura)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_factura)
            val respuesta = EBaseDeDatos.tablaFactura!!.eliminarFactura(id.text.toString().toInt())
            if (respuesta) mostrarSnackbar("Factura eliminada")
        }
    }
}
