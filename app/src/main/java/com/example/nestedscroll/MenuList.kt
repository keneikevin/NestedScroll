package com.example.nestedscroll

import android.os.Parcel
import android.os.Parcelable

data class MenuList(val data: List<LocationData>)
data class LocationData(val Name: String?,
                        val subtitle: String?,
                        val price: String?,
                        val url: String?,
                        val childLocations: List<LocationData>?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(), null

    ) 
    

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Name)
        parcel.writeString(subtitle)
        parcel.writeString(price)
        parcel.writeString(url)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationData> {
        override fun createFromParcel(parcel: Parcel): LocationData {
            return LocationData(parcel)
        }

        override fun newArray(size: Int): Array<LocationData?> {
            return arrayOfNulls(size)
        }
    }

}