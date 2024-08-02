package com.example.a2024accgr1esps

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperFactura(contexto: Context?): SQLiteOpenHelper(
    contexto,
    "facturasDB",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFactura =
            """
                CREATE TABLE FACTURA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cliente varchar(50),
                    fecha varchar(50),
                    descuento REAL,
                    pagada BOOLEAN
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFactura)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createFactura(cliente: String, fecha: String, descuento: Double, pagada: Boolean): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("cliente", cliente)
        valoresAGuardar.put("fecha", fecha)
        valoresAGuardar.put("descuento", descuento)
        valoresAGuardar.put("pagada", pagada)
        val resultado = basedatosEscritura.insert("FACTURA", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultado.toInt() != -1
    }

    fun eliminarFactura(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "FACTURA",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarFactura(id: Int, cliente: String, fecha: String, descuento: Double, pagada: Boolean): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("cliente", cliente)
        valoresAActualizar.put("fecha", fecha)
        valoresAActualizar.put("descuento", descuento)
        valoresAActualizar.put("pagada", pagada)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "FACTURA",
            valoresAActualizar,
            "id=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return resultadoActualizacion.toInt() != -1
    }

    fun consultarFacturaPorID(id: Int): BFactura? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FACTURA WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            arregloParametrosConsultaLectura
        )
        val existeAlMenosUno = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BFactura>()
        if (existeAlMenosUno) {
            do {
                val factura = BFactura(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getInt(4) > 0
                )
                arregloRespuesta.add(factura)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if (arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }
}
