package com.example.a2024accgr1esps

import android.os.Parcel
import android.os.Parcelable

class BFactura(
    var id:Int,
    var cliente:String,
    var fecha:String,
    var descuento:Double,
    var pagada:Boolean

    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun toString(): String {
        return "$cliente ${fecha} $descuento $pagada"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(cliente)
        parcel.writeString(fecha)
        parcel.writeDouble(descuento)
        parcel.writeByte(if (pagada) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BFactura> {
        override fun createFromParcel(parcel: Parcel): BFactura {
            return BFactura(parcel)
        }

        override fun newArray(size: Int): Array<BFactura?> {
            return arrayOfNulls(size)
        }
    }

}