package com.example.a2024accgr1esps

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperDetalleFactura(contexto: Context?): SQLiteOpenHelper(
    contexto,
    "detalleFacturasDB",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaDetalleFactura =
            """
                CREATE TABLE DETALLE_FACTURA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cantidad INTEGER,
                    producto varchar(50),
                    precio REAL,
                    impuesto REAL,
                    enviado BOOLEAN
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaDetalleFactura)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createDetalleFactura(cantidad: Int, producto: String, precio: Double, impuesto: Double, enviado: Boolean): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("cantidad", cantidad)
        valoresAGuardar.put("producto", producto)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("impuesto", impuesto)
        valoresAGuardar.put("enviado", enviado)
        val resultado = basedatosEscritura.insert("DETALLE_FACTURA", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultado.toInt() != -1
    }

    fun eliminarDetalleFactura(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "DETALLE_FACTURA",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarDetalleFactura(id: Int, cantidad: Int, producto: String, precio: Double, impuesto: Double, enviado: Boolean): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("cantidad", cantidad)
        valoresAActualizar.put("producto", producto)
        valoresAActualizar.put("precio", precio)
        valoresAActualizar.put("impuesto", impuesto)
        valoresAActualizar.put("enviado", enviado)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "DETALLE_FACTURA",
            valoresAActualizar,
            "id=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return resultadoActualizacion.toInt() != -1
    }

    fun consultarDetalleFacturaPorID(id: Int): BDetalleFactura? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM DETALLE_FACTURA WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            arregloParametrosConsultaLectura
        )
        val existeAlMenosUno = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BDetalleFactura>()
        if (existeAlMenosUno) {
            do {
                val detalleFactura = BDetalleFactura(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getInt(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getDouble(4),
                    resultadoConsultaLectura.getInt(5) > 0
                )
                arregloRespuesta.add(detalleFactura)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if (arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }
}
