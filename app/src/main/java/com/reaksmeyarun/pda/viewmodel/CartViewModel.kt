package com.reaksmeyarun.pda.viewmodel

import android.app.Dialog
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.adapter.CartAdapter
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.firebaseRepo.RequestClearAllCarts
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.AlertUtil
import com.reaksmeyarun.pda.utils.DataSnapShotConvertUtils
import com.reaksmeyarun.pda.view.activity.CartActivity

class CartViewModel (var activity : CartActivity) : ViewModel(){

    fun requestCart(cartAdapter : CartAdapter){
        val requestCart = RequestCart(activity)
        requestCart.onSingleValueListener(object : FirebaseGetListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data =
                    DataSnapShotConvertUtils.dataSnapShotToArrayList(RequestCart.ResponseCart()::class.java, dataSnapshot)
                if(data.size > 0){
                    activity.cartLists.addAll(data)
                    cartAdapter.addItems(activity.cartLists)
                }else{
                    Toast.makeText(activity, "No Items have been order", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelListener(databaseError: DatabaseError) {

            }
        })
        requestCart.execute()
    }

    fun handleClearAll(view : View){
        if(activity.cartLists.size > 0){
            AlertUtil.alertConfirm(activity, "Khmer2020", "Are you sure to clear all the cart?", object : AlertUtil.DecisionCallback{
                override fun onConfirm(dialog: Dialog) {
                    val requestClearAllCarts = RequestClearAllCarts(activity)
                    requestClearAllCarts.rqClearCartModel.orderId = UserSession.getInstance(activity).getUserId()
                    requestClearAllCarts.listener(object : FireBaseListener{
                        override fun onFailureListener() {

                        }

                        override fun <TResult> onCompleteListener(task: Task<TResult>) {
                            activity.cartAdapter.clearAll()
                        }

                    })
                    requestClearAllCarts.execute()
                }

                override fun onReject(dialog: Dialog) {
                    dialog.dismiss()
                }

            })
        }
    }
}