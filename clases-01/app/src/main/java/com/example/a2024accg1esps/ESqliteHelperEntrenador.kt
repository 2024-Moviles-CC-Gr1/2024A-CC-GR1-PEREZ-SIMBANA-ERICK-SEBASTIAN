package com.example.a2024accg1esps

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class ESqliteHelperEntrenador(contexto: Context?): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre varchar(50),
                    descripcion varchar(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createEntrenador(nombre: String, descripcion: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultado = basedatosEscritura.insert("ENTRENADOR", null, valoresAGuardar)
        basedatosEscritura.close()
        return if(resultado.toInt() == -1) false else true
    }

    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        //Consulta SQL: where ... ID=? AND NOMBRE=?
        val parametrosConsultaDelete = arrayOf(id.toString())
        var resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR",
            "id=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }
    fun actualizarEntrenadorFormulario (
        nombre: String, descripcion: String, id: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion",descripcion)
        //where: ...
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR",
            valoresAActualizar, //nombre= Jeniffer, descripcion= B
            "id=?", //id=1
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarEntrenadorPorID(id: Int):BEntrenador?{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            arregloParametrosConsultaLectura
        )
        //Logica busqueda
        //Recibimos un arreglo (puede ser nulo)
        //Llenar un arreglo de Entrenadores
        //Si esta vacio, el arreglo no tiene elementos
        val existeAlMenosUno = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BEntrenador>()
        if (existeAlMenosUno){
            do{
                val entrenador = BEntrenador(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2)
                )
                arregloRespuesta.add(entrenador)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return  arregloRespuesta[0]
    }


}

