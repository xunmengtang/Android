package com.ig.iginnovation.superapp.igsuperapprider.util

import android.app.Dialog
import android.content.Context
import com.ig.iginnovation.superapp.igsuperapprider.R
import kotlinx.android.synthetic.main.alert_message.*

object PopupMsg {
    fun alert(context: Context?,msg: String){
        if(context!=null){
            val dialog = Dialog(context, R.style.Theme_AppCompat_Dialog)
            dialog.setContentView(R.layout.alert_message)
            dialog.txtMsg.text = msg
            dialog.popupBtnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    fun alert(context: Context?,msg: String,callBack: OnClickButtonCloseCallBack){
        if(context!=null){
            val dialog = Dialog(context, R.style.Theme_AppCompat_Dialog)
            dialog.setContentView(R.layout.alert_message)
            dialog.txtMsg.text = msg
            dialog.popupBtnClose.setOnClickListener {
                callBack.onCloseCallBack()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    interface OnClickButtonCloseCallBack{
        fun onCloseCallBack()
    }
}