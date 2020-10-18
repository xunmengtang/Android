package com.reaksmeyarun.pda.model

import android.os.Parcel
import android.os.Parcelable

class CategoryModel(var userSession : UserModel.UserSession ?= null,
                    var categoryInformation : CategoryInformationModel ?= null,
                    var categorySize : Int ?= 0,
                    var timestamp : String ?= "",
                    var status : Int ?= 0)