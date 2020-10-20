package com.reaksmeyarun.pda.firebaseRepo.fav

import android.app.Activity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener

class RequestCategoryFav(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestCategory"
    private var firebaseGetListener : FirebaseGetListener ?= null
    fun onSingleValueListener(listener: FirebaseGetListener){
        this.firebaseGetListener = listener
    }
    fun execute(){
        firebaseGetListener?.let {
            get(TAG, categoryQueryRequest(), it)
        }
    }

    private fun categoryQueryRequest() : DatabaseReference{
        return databaseReference(FirebaseConstance.CATEGORY_NODE)
    }

    class ResponseCategory(
        var categoryName : String?= null,
        var createBy : String?= null,
        var description : String ?= "",
        var id : String ?= "",
        var image : Image?= null
    )

    class Image(
        var id : String ?= "",
        var url : String ?= ""
    )
}