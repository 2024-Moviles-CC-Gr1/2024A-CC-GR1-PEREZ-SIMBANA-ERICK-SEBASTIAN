package com.example.a2024accg1esps

class BEntrenador(
    var id: Int,
    var nombre: String,
    var descripcion: String?
) {
    override fun toString(): String {
        return "$nombre ${descripcion}"
    }

}