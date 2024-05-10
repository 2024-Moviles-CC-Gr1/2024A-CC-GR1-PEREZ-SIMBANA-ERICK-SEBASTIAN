import java.util.*

fun main() {
    print("Hola mundo")
    //INMUTABLES (No se reasigna "=")
    val inmutable: String = "\nHola MUNDOOOO"
    print(inmutable)
    //MUTABLE
    var mutable: String = "\nHola mundo"
    println(mutable)
    mutable = "\nAdios mundo"
    println(mutable)
    // VAL > VAR
    // Evitar cambios en distintas referencias a un mismo objeto
    // Buena practica siempre usar val

    // Duck Typing
    val ejemplodeDuck = "JELSPISP"
    // Tipos de variables
    var ejemplo: Int = 12
    ejemplodeDuck.trim()
    val nombreProf:String = "Adrien Eguez"
    val sueldo: Double = 1.2
    val estadocivil = 'C'
    val mayoredad:Boolean = true
    val fecha: Date = Date()

    val bandera = "R"

    when(bandera){
        "R" ->{
            println("Rojo")
        }
        "A" -> {
            println("Azul")
        }
        else -> {
            println("No hay color")
        }
    }

    calcularSueldo(10.00)
    calcularSueldo(10.00,15.00,20.00)
    //Named parameters
    calcularSueldo(10.00, bonoEspecial = 20.00)

    val sumaUno = suma(1,1)
    val sumaDos = suma(null,1)
    val sumaTres = suma(1,null)
    val sumaCuatro = suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(suma.pi)
    println(suma.elevarAlCuadrado(2))
    print(suma.historialSumas)
}

fun imprimirNombre(nombre:String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo:Double,
    tasa:Double = 12.00,
    bonoEspecial:Double? = null //Puede ser nulo o double
):Double{
    if(bonoEspecial==null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

abstract class Numerosjava{
    protected val numeroUni: Int
    private val numeroDos: Int
    constructor(uno: Int, dos: Int){
        this.numeroUni = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}

abstract class Numeros(
    protected val numero: Int,
    protected val numeroUni: Int,
){
    init {
        println("Inicializado")
    }
}

class suma(
    unoParametro: Int,
    dosParametro: Int,
): Numeros(unoParametro, dosParametro){
    public val soyPublicoExplicito:String = "Explicito"
    val soyPublicoImplicito:String = "Implicito"
    init{
        this.numeroUni
        this.numero
        numeroUni
        numero
        this.soyPublicoImplicito
        soyPublicoExplicito
    }
    constructor(
        unoParametro:Int?,
        dosParametro: Int
    ):this(
        if (unoParametro==null) 0 else unoParametro,dosParametro
    )

    constructor(
        unoParametro: Int,
        dosParametro: Int?,
    ):this(
        unoParametro,
        if (dosParametro==null) 0 else dosParametro
    )

    constructor(
        unoParametro: Int?,
        dosParametro: Int?,
    ):this(
        if (dosParametro==null) 0 else dosParametro,
        if (unoParametro==null) 0 else unoParametro
    )

    fun sumar(): Int{
        val total = numero + numeroUni
        agregarHistorial(total)
        return total
    }
    companion object{
        val pi = 3.14
        fun elevarAlCuadrado(num: Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valortotalsuma:Int){
            historialSumas.add(valortotalsuma)
        }
    }
}
