import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Factura(
    var id: Int = 0,
    var fecha: LocalDate,
    var cliente: String,
    var descuento: Double = 0.0,
    var pagada: Boolean = false
) {
    private val detalles: MutableList<DetalleFactura> = mutableListOf()

    fun crearDetalle(producto: String, cantidad: Int, precio: Double, impuesto: Double, enviado: Boolean) {
        val detalle = DetalleFactura(producto, cantidad, precio, impuesto, enviado)
        detalles.add(detalle)
        guardarEnArchivo()
        println("Se ha creado el detalle de factura: $detalle")
    }


    fun leerDetalles() {
        if (detalles.isNotEmpty()) {
            println("Detalles de la factura:")
            detalles.forEachIndexed { index, detalle ->
                println("$index. $detalle")
            }
        } else {
            println("No hay detalles de factura registrados.")
        }
    }

    fun actualizarDetalle(index: Int, producto: String, cantidad: Int, precio: Double, impuesto: Double, enviado: Boolean) {
        if (index >= 0 && index < detalles.size) {
            detalles[index].actualizarDetalle(producto, cantidad, precio, impuesto, enviado)
            guardarEnArchivo()
            println("Se ha actualizado el detalle de factura: ${detalles[index]}")
        } else {
            println("Índice fuera de rango")
        }
    }

    fun eliminarDetalle(index: Int) {
        if (index >= 0 && index < detalles.size) {
            val detalleEliminado = detalles.removeAt(index)
            guardarEnArchivo()
            println("Se ha eliminado el detalle de factura: $detalleEliminado")
        } else {
            println("Índice fuera de rango")
        }
    }

    fun guardarEnArchivo() {
        val file = File(NOMBRE_ARCHIVO_DEFECTO)
        file.writeText(
            "ID: $id\n" +
                    "Fecha: ${fecha.format(DateTimeFormatter.ISO_DATE)}\n" +
                    "Cliente: $cliente\n" +
                    "Descuento: $descuento\n" +
                    "Pagada: $pagada\n" +
                    "Detalles:\n" +
                    detalles.joinToString("\n") { it.toStringParaArchivo() }
        )
        println("La factura se ha guardado en el archivo $NOMBRE_ARCHIVO_DEFECTO")
    }

    fun actualizarFactura(fecha: LocalDate, cliente: String, descuento: Double, pagada: Boolean) {
        this.fecha = fecha
        this.cliente = cliente
        this.descuento = descuento
        this.pagada = pagada
        guardarEnArchivo()
        println("Se ha actualizado la factura: $this")
    }

    companion object {
        private const val NOMBRE_ARCHIVO_DEFECTO = "facturas.txt"

        fun cargarDesdeArchivo(): MutableList<Factura> {
            val facturas = mutableListOf<Factura>()
            val file = File(NOMBRE_ARCHIVO_DEFECTO)
            if (file.exists()) {
                val lineas = file.readLines()
                var i = 0
                while (i < lineas.size) {
                    val id = lineas[i].substringAfter(": ").toInt()
                    val fecha = LocalDate.parse(lineas[i + 1].substringAfter(": "), DateTimeFormatter.ISO_DATE)
                    val cliente = lineas[i + 2].substringAfter(": ")
                    val descuento = lineas[i + 3].substringAfter(": ").toDouble()
                    val pagada = lineas[i + 4].substringAfter(": ").toBoolean()

                    val factura = Factura(id, fecha, cliente, descuento, pagada)
                    i += 6
                    while (i < lineas.size && lineas[i].isNotBlank()) {
                        val (producto, cantidad, precio, impuesto, enviado) = lineas[i].split(", ")
                        factura.crearDetalle(
                            producto,
                            cantidad.substringAfter(": ").toInt(),
                            precio.substringAfter(": ").toDouble(),
                            impuesto.substringAfter(": ").toDouble(),
                            enviado.substringAfter(": ").toBoolean()
                        )
                        i++
                    }
                    facturas.add(factura)
                }
            } else {
                println("El archivo $NOMBRE_ARCHIVO_DEFECTO no existe")
            }
            return facturas
        }
    }
}