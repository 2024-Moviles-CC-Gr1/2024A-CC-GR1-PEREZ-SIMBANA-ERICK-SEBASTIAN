import java.time.LocalDate

fun main() {
    val facturas: MutableList<Factura> = Factura.cargarDesdeArchivo()
    var opcion: Int

    do {
        mostrarMenu()
        opcion = readLine()?.toIntOrNull() ?: continue

        when (opcion) {
            1 -> crearFactura(facturas)
            2 -> listarFacturas(facturas)
            3 -> actualizarFactura(facturas)
            4 -> eliminarFactura(facturas)
            5 -> crearDetalleFactura(facturas)
            6 -> actualizarDetalleFactura(facturas)
            7 -> eliminarDetalleFactura(facturas)
            8 -> break
            else -> println("Opción inválida")
        }
    } while (true)
}

fun mostrarMenu() {
    println(
        """
        === MENÚ ===
        1. Crear Factura
        2. Listar Facturas
        3. Actualizar Factura
        4. Eliminar Factura
        5. Crear Detalle de Factura
        6. Actualizar Detalle de Factura
        7. Eliminar Detalle de Factura
        8. Salir
        Ingrese una opción:
    """.trimIndent()
    )
}

fun crearFactura(facturas: MutableList<Factura>) {
    print("Ingrese la fecha (yyyy-mm-dd): ")
    val fecha = readLine()?.let { LocalDate.parse(it) } ?: return
    print("Ingrese el cliente: ")
    val cliente = readLine() ?: return
    print("Ingrese el descuento (%): ")
    val descuento = readLine()?.toDoubleOrNull() ?: return
    print("¿La factura está pagada? (true/false): ")
    val pagada = readLine()?.toBoolean() ?: return

    val nuevaFactura = Factura(facturas.size + 1, fecha, cliente, descuento, pagada)
    facturas.add(nuevaFactura)
    println("Factura creada: $nuevaFactura")
}

fun listarFacturas(facturas: List<Factura>) {
    if (facturas.isNotEmpty()) {
        facturas.forEachIndexed { index, factura ->
            println("${index + 1}. $factura")
        }
    } else {
        println("No hay facturas registradas")
    }
}

fun actualizarFactura(facturas: MutableList<Factura>) {
    print("Ingrese el índice de la factura a actualizar: ")
    val index = readLine()?.toIntOrNull()?.minus(1) ?: return

    if (index in facturas.indices) {
        print("Ingrese la nueva fecha (yyyy-mm-dd): ")
        val fecha = readLine()?.let { LocalDate.parse(it) } ?: return
        print("Ingrese el nuevo cliente: ")
        val cliente = readLine() ?: return
        print("Ingrese el nuevo descuento (%): ")
        val descuento = readLine()?.toDoubleOrNull() ?: return
        print("¿La factura está pagada? (true/false): ")
        val pagada = readLine()?.toBoolean() ?: return

        facturas[index].actualizarFactura(fecha, cliente, descuento, pagada)
        println("Factura actualizada: ${facturas[index]}")
    } else {
        println("Índice fuera de rango")
    }
}

fun eliminarFactura(facturas: MutableList<Factura>) {
    print("Ingrese el índice de la factura a eliminar: ")
    val index = readLine()?.toIntOrNull()?.minus(1) ?: return

    if (index in facturas.indices) {
        val facturaEliminada = facturas.removeAt(index)
        println("Factura eliminada: $facturaEliminada")
    } else {
        println("Índice fuera de rango")
    }
}

fun crearDetalleFactura(facturas: MutableList<Factura>) {
    print("Ingrese el índice de la factura: ")
    val index = readLine()?.toIntOrNull()?.minus(1) ?: return

    if (index in facturas.indices) {
        print("Ingrese el producto: ")
        val producto = readLine() ?: return
        print("Ingrese la cantidad: ")
        val cantidad = readLine()?.toIntOrNull() ?: return
        print("Ingrese el precio: ")
        val precio = readLine()?.toDoubleOrNull() ?: return
        print("Ingrese el impuesto (%): ")
        val impuesto = readLine()?.toDoubleOrNull() ?: return
        print("¿El producto fue enviado? (true/false): ")
        val enviado = readLine()?.toBoolean() ?: return

        facturas[index].crearDetalle(producto, cantidad, precio, impuesto, enviado)
    } else {
        println("Índice fuera de rango")
    }
}

fun actualizarDetalleFactura(facturas: MutableList<Factura>) {
    print("Ingrese el índice de la factura: ")
    val indexFactura = readLine()?.toIntOrNull()?.minus(1) ?: return

    if (indexFactura in facturas.indices) {
        facturas[indexFactura].leerDetalles()
        print("Ingrese el índice del detalle a actualizar: ")
        val indexDetalle = readLine()?.toIntOrNull() ?: return

        print("Ingrese el nuevo producto: ")
        val producto = readLine() ?: return
        print("Ingrese la nueva cantidad: ")
        val cantidad = readLine()?.toIntOrNull() ?: return
        print("Ingrese el nuevo precio: ")
        val precio = readLine()?.toDoubleOrNull() ?: return
        print("Ingrese el nuevo impuesto (%): ")
        val impuesto = readLine()?.toDoubleOrNull() ?: return
        print("¿El producto fue enviado? (true/false): ")
        val enviado = readLine()?.toBoolean() ?: return

        facturas[indexFactura].actualizarDetalle(indexDetalle, producto, cantidad, precio, impuesto, enviado)
    } else {
        println("Índice de factura fuera de rango")
    }
}

fun eliminarDetalleFactura(facturas: MutableList<Factura>) {
    print("Ingrese el índice de la factura: ")
    val indexFactura = readLine()?.toIntOrNull()?.minus(1) ?: return

    if (indexFactura in facturas.indices) {
        facturas[indexFactura].leerDetalles()
        print("Ingrese el índice del detalle a eliminar: ")
        val indexDetalle = readLine()?.toIntOrNull() ?: return

        facturas[indexFactura].eliminarDetalle(indexDetalle)
    } else {
        println("Índice de factura fuera de rango")
    }
}