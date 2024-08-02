package com.example.a2024accgr1esps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudDetalleFactura : AppCompatActivity() {
    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_sqlite_detalle_factura),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_detalle_factura)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd_detalle_factura)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_detalle_factura)
            val cantidad = findViewById<EditText>(R.id.input_cantidad)
            val producto = findViewById<EditText>(R.id.input_producto)
            val precio = findViewById<EditText>(R.id.input_precio)
            val impuesto = findViewById<EditText>(R.id.input_impuesto)
            val enviado = findViewById<EditText>(R.id.input_enviado)
            val detalleFactura = EBaseDeDatos.tablaDetalleFactura!!.consultarDetalleFacturaPorID(id.text.toString().toInt())
            if (detalleFactura == null) {
                mostrarSnackbar("Detalle de factura no encontrado")
                id.setText("")
                cantidad.setText("")
                producto.setText("")
                precio.setText("")
                impuesto.setText("")
                enviado.setText("")
            } else {
                id.setText(detalleFactura.id.toString())
                cantidad.setText(detalleFactura.cantidad.toString())
                producto.setText(detalleFactura.producto)
                precio.setText(detalleFactura.precio.toString())
                impuesto.setText(detalleFactura.impuesto.toString())
                enviado.setText(if (detalleFactura.enviado) "Sí" else "No")
                mostrarSnackbar("Detalle de factura encontrado")
            }
        }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd_detalle_factura)
        botonCrearBDD.setOnClickListener {
            val cantidad = findViewById<EditText>(R.id.input_cantidad)
            val producto = findViewById<EditText>(R.id.input_producto)
            val precio = findViewById<EditText>(R.id.input_precio)
            val impuesto = findViewById<EditText>(R.id.input_impuesto)
            val enviado = findViewById<EditText>(R.id.input_enviado)
            val respuesta = EBaseDeDatos.tablaDetalleFactura!!.createDetalleFactura(
                cantidad.text.toString().toInt(),
                producto.text.toString(),
                precio.text.toString().toDouble(),
                impuesto.text.toString().toDouble(),
                enviado.text.toString().equals("Sí", true)
            )
            if (respuesta) mostrarSnackbar("Detalle de factura creado")
        }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd_detalle_factura)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_detalle_factura)
            val cantidad = findViewById<EditText>(R.id.input_cantidad)
            val producto = findViewById<EditText>(R.id.input_producto)
            val precio = findViewById<EditText>(R.id.input_precio)
            val impuesto = findViewById<EditText>(R.id.input_impuesto)
            val enviado = findViewById<EditText>(R.id.input_enviado)
            val respuesta = EBaseDeDatos.tablaDetalleFactura!!.actualizarDetalleFactura(
                id.text.toString().toInt(),
                cantidad.text.toString().toInt(),
                producto.text.toString(),
                precio.text.toString().toDouble(),
                impuesto.text.toString().toDouble(),
                enviado.text.toString().equals("Sí", true)
            )
            if (respuesta) mostrarSnackbar("Detalle de factura actualizado")
        }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd_detalle_factura)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_detalle_factura)
            val respuesta = EBaseDeDatos.tablaDetalleFactura!!.eliminarDetalleFactura(id.text.toString().toInt())
            if (respuesta) mostrarSnackbar("Detalle de factura eliminado")
        }
    }
}
