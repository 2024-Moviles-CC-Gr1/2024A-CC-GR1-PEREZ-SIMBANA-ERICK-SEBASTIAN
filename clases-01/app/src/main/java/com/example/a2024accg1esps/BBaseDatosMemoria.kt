package com.example.a2024accg1esps

class BBaseDatosMemoria {
    companion object{
        val arregloDBBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloDBBEntrenador
                .add(
                    BEntrenador(1, "Adrian","a@a.com")
                )
            arregloDBBEntrenador
                .add(
                    BEntrenador(2, "Erick","b@b.com")
                )
            arregloDBBEntrenador
                .add(
                    BEntrenador(3, "Lissette","c@c.com")
                )

        }
    }
}