package com.example.nestedscroll.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
    var Name:String ?= null,
    var image:String ?= null,
    var price: Long ?=null,
    var details: String ?=null

): Parcelable