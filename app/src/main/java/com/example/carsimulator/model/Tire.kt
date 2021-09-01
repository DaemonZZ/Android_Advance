package com.example.carsimulator.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Tire():Parcelable {
    @PrimaryKey(autoGenerate = false)
    var id:Int = 0
    var temperature:Float = 0f
    var pressure : Float = 0f



    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readInt()
        temperature = parcel.readFloat()
        pressure = parcel.readFloat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeFloat(temperature)
        parcel.writeFloat(pressure)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tire> {
        override fun createFromParcel(parcel: Parcel): Tire {
            return Tire(parcel)
        }

        override fun newArray(size: Int): Array<Tire?> {
            return arrayOfNulls(size)
        }
    }

    fun getTempSeekBarValue() = temperature.toInt()
    fun getPressSeekBarValue() = (pressure*10).toInt()

    fun setTempSeekBarValue(temp:Int){
        temperature = temp.toFloat()
    }

    fun setPressSeekBarValue(pres:Int){
       pressure = pres.toFloat()/10
    }
}