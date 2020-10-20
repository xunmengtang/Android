package com.reaksmeyarun.pda.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import com.reaksmeyarun.pda.R
import kotlinx.android.synthetic.main.alert.*
import kotlinx.android.synthetic.main.alert_confirm.*
import kotlinx.android.synthetic.main.alert_confirm.alertTitle
import kotlinx.android.synthetic.main.alert_confirm.layout
import kotlinx.android.synthetic.main.alert_confirm.popupBtnNo
import kotlinx.android.synthetic.main.alert_confirm.popupBtnYes
import kotlinx.android.synthetic.main.alert_confirm.view.*
import kotlinx.android.synthetic.main.alert_confirm.view.txtMsg


object AlertUtil {
    interface DecisionCallback {
        fun onConfirm(dialog: Dialog)
        fun onReject(dialog: Dialog)
    }
    fun init(context: Context, msg : String) : Dialog {
        val dialog = Dialog(context, R.style.RoundedCornersDialog)
        dialog.setContentView(R.layout.alert)
        dialog.txtAlertMsg.text = msg
        dialog.popupBtnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        return dialog
    }
    fun alertConfirm(context: Context?, title: String, msg: String, callBack: DecisionCallback){
        if(context!=null){
            val dialog = Dialog(context, R.style.RoundedCornersDialog)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.alert_confirm)
            dialog.layout.txtMsg.gravity = Gravity.CENTER
            dialog.layout.layoutParams.width = ScreenSize.getWidthPixels(context as Activity) - (ConverterPxAndDp.init().toPx(context.resources.getDimension(R.dimen.dimen_32)))
            dialog.alertTitle.text = title
            dialog.txtMsg.text = msg
            dialog.popupBtnNo.setOnClickListener {
                dialog.dismiss()
                callBack.onReject(dialog)
            }
            dialog.popupBtnYes.setOnClickListener {
                dialog.dismiss()
                callBack.onConfirm(dialog)
            }
            dialog.show()
        }
    }
}