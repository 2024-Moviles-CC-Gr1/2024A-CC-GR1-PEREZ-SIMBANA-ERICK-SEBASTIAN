package com.example.a2024accgr1esps

import android.os.Parcel
import android.os.Parcelable

class BDetalleFactura(
    var id:Int,
    var cantidad:Int,
    var producto:String,
    var precio:Double,
    var impuesto:Double,
    var enviado:Boolean

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun toString(): String {
        return "$producto ${precio} $impuesto $enviado"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(cantidad)
        parcel.writeString(producto)
        parcel.writeDouble(precio)
        parcel.writeDouble(impuesto)
        parcel.writeByte(if (enviado) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BDetalleFactura> {
        override fun createFromParcel(parcel: Parcel): BDetalleFactura {
            return BDetalleFactura(parcel)
        }

        override fun newArray(size: Int): Array<BDetalleFactura?> {
            return arrayOfNulls(size)
        }
    }

}