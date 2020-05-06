package com.reaksmeyarun.pda.utils

import android.app.Dialog
import android.content.Context
import com.reaksmeyarun.pda.R
import kotlinx.android.synthetic.main.layout_alert_message.*
import kotlinx.android.synthetic.main.layout_alert_message.txtMsg
import kotlinx.android.synthetic.main.layout_alert_yes_no_message.*

object PopupMsg {
    fun alert(context: Context?,msg: String){
        if(context!=null){
            val dialog = Dialog(context, R.style.Theme_AppCompat_Dialog)
            dialog.setContentView(R.layout.layout_alert_message)
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
            dialog.setContentView(R.layout.layout_alert_message)
            dialog.txtMsg.text = msg
            dialog.popupBtnClose.setOnClickListener {
                callBack.onCloseCallBack()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    fun alert(context: Context?, msg : String, callBack : OnClickButtonYesNoCallBack){
        if(context!=null){
            val dialog = Dialog(context, R.style.Theme_AppCompat_Dialog)
            dialog.setContentView(R.layout.layout_alert_yes_no_message)
            dialog.txtMsg.text = msg
            dialog.popupBtnNo.setOnClickListener {
                callBack.onNoCallBack()
                dialog.dismiss()
            }
            dialog.popupBtnYes.setOnClickListener{
                callBack.onYesCallBack()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    interface OnClickButtonCloseCallBack{
        fun onCloseCallBack()
    }
    interface OnClickButtonYesNoCallBack{
        fun onYesCallBack()
        fun onNoCallBack()
    }
}