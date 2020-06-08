@file:Suppress("DEPRECATION")

package com.reaksmeyarun.pda.customView

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import com.reaksmeyarun.pda.R


class BaseAppBackground : View {

    private var startAngle = 0f
    private var sweepAngle = 0f
    private var useCenter = true
    private var wHeight : Float = 0f
    private var wWidth : Float = 0f
    private var mContext : Context? = null
    constructor(context: Context) : super(context){
        mContext = context
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!,attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context!!,attrs)
    }
    private fun init(context: Context , attrs: AttributeSet?){
        val typeArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperAppBG)
        startAngle = typeArray.getFloat(R.styleable.SuperAppBG_start_angel, DEFAULT_START_ANGLE)
        sweepAngle = typeArray.getFloat(R.styleable.SuperAppBG_sweep_angle, DEFAULT_SWEEP_ANGLE)
        useCenter = typeArray.getBoolean(R.styleable.SuperAppBG_use_center, DEFAULT_USE_CENTER)
        mContext = context

        val ma  = context as Activity
        val displayMetrics = DisplayMetrics()
        ma.windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        wHeight = (displayMetrics.heightPixels).toFloat()
        wWidth = (displayMetrics.widthPixels).toFloat()

        Log.e("OvalShape","Screen heigt: $wHeight")

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawColor(resources.getColor(R.color.white))
//        canvas!!.drawColor(resources.getColor(R.color.baseColor))
        Log.e("OvalShape","height $wHeight")
        Log.e("OvalShape","width $wWidth")
        val radius = wHeight
        val alpha = 225
//        val alpha = 180
        //bottom oval left
        val paint1 = Paint()
        paint1.style = Paint.Style.FILL
        paint1.color = Color.argb(alpha, 0, 229, 255)
//        paint1.color = Color.argb(alpha, 255, 255, 255)
        canvas.drawCircle(wWidth / 10, (radius * 2) - wHeight / 4, radius, paint1)

        //bottom oval center
//        var paint = Paint()
//        paint.style = Paint.Style.FILL
//        paint.color = Color.argb(alpha, 73, 238, 255)
////        paint.color = Color.argb(alpha, 255, 255, 255)
//        canvas.drawCircle(wWidth / 2 + wWidth / 10, (radius * 2) - (wHeight / 5), radius, paint)
        }
    companion object {
        private const val DEFAULT_START_ANGLE : Float = 0f
        private const val DEFAULT_SWEEP_ANGLE : Float = 0f
        private const val DEFAULT_USE_CENTER : Boolean = true
    }
}
