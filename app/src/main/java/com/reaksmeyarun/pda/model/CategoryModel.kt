package com.reaksmeyarun.pda.model

import android.os.Parcel
import android.os.Parcelable

class CategoryModel() : Parcelable {
    var userSession = UserModel.UserSession()
    var categoryInformation = CategoryInformationModel()
    var categorySize : Int ?= 0
    var timestamp : String ?= ""
    var status : Int ?= 0

    constructor(parcel: Parcel) : this() {
        categorySize = parcel.readValue(Int::class.java.classLoader) as? Int
        timestamp = parcel.readString()
        status = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun toString(): String = "CategoryModel(userSession=$userSession, categoryInformation=$categoryInformation, categorySize=$categorySize, timestamp=$timestamp, status=$status)"
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(categorySize)
        parcel.writeString(timestamp)
        parcel.writeValue(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryModel> {
        override fun createFromParcel(parcel: Parcel): CategoryModel {
            return CategoryModel(parcel)
        }

        override fun newArray(size: Int): Array<CategoryModel?> {
            return arrayOfNulls(size)
        }
    }
}