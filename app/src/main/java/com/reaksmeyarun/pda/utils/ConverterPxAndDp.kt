package com.reaksmeyarun.pda.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue

class ConverterPxAndDp{
    fun toDp(px : Int): Int = (px / Resources.getSystem().displayMetrics.densityDpi)
    fun toPx(dp : Float): Int = (dp * Resources.getSystem().displayMetrics.densityDpi).toInt() / DisplayMetrics.DENSITY_DEFAULT
    fun spToPx(sp: Float, context: Context): Int  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics).toInt()
    fun pxToSp(px: Float, context: Context): Int  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.resources.displayMetrics).toInt()
    fun pxToDp(px: Float, context: Context): Int  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.resources.displayMetrics).toInt()

    companion object {
        private val instance: ConverterPxAndDp? = null
        fun init(): ConverterPxAndDp {
            return instance ?: ConverterPxAndDp()
        }
    }
}