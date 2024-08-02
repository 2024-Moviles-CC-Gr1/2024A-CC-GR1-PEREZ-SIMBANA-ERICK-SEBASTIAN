package com.example.a2024accg1esps

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ECrudEntrenador : AppCompatActivity() {
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_sqlite),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .consultarEntrenadorPorID(id.text.toString().toInt())
            if(entrenador == null){
                mostrarSnackbar("No se ha encontrado el entrenador")
                id.setText("")
                nombre.setText("")
                descripcion.setText("")
            }else{
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcion.setText(entrenador.descripcion)
                mostrarSnackbar("Se ha encontrado el entrenador")
            }
        }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .createEntrenador(nombre.text.toString(), descripcion.text.toString())
            if(entrenador)
                mostrarSnackbar("Se ha creado el entrenador")
            else
                mostrarSnackbar("No se ha podido crear el entrenador")
        }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val respuesta = EBaseDeDatos.tablaEntrenador!!
                .actualizarEntrenadorFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackbar("Entrenador actualizado")
        }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val respuesta = EBaseDeDatos.tablaEntrenador!!
                .eliminarEntrenadorFormulario(id.text.toString().toInt())
            if(respuesta) mostrarSnackbar("Entrenador eliminado")
        }
    }


}