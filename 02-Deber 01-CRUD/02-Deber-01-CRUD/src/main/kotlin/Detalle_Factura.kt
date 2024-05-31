data class DetalleFactura(
    var producto: String,
    var cantidad: Int,
    var precio: Double,
    var impuesto: Double,
    var enviado: Boolean
) {
    fun actualizarDetalle(producto: String, cantidad: Int, precio: Double, impuesto: Double, enviado: Boolean) {
        this.producto = producto
        this.cantidad = cantidad
        this.precio = precio
        this.impuesto = impuesto
        this.enviado = enviado
    }

    fun calcularSubtotal(): Double {
        return cantidad * precio * (1 + impuesto / 100)
    }

    override fun toString(): String {
        return "Producto: $producto, Cantidad: $cantidad, Precio: $precio, Impuesto: $impuesto%, Enviado: $enviado, Subtotal: ${calcularSubtotal()}"
    }

    fun toStringParaArchivo(): String {
        return "Producto: $producto, Cantidad: $cantidad, Precio: $precio, Impuesto: $impuesto, Enviado: $enviado"
    }
}