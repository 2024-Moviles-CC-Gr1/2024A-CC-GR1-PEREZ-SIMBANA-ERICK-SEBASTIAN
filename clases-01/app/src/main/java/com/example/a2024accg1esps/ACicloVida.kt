package com.example.a2024accg1esps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    var textoGlobal = ""

    fun mostrarSnackbar(texto: String) {
        textoGlobal += texto
        val snackbar = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mostrarSnackbar("En OnCreate")
        setContentView(R.layout.activity_aciclo_vida)
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("En OnStart")

    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("En OnResume")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("En OnPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("En OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("En OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("En OnDestroy")
    }
}