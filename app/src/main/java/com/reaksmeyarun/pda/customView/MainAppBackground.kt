package com.example.myapplication.customview

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
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import com.ig.iginnovation.superapp.igsuperapprider.R


class SuperAppBackgroundHalfRed : View {

//    private var startAngle = 0f
//    private var sweepAngle = 0f
//    private var useCenter = true
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
//        val typeArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperAppBG)
//        startAngle = typeArray.getFloat(R.styleable.SuperAppBG_start_angel, DEFAULT_START_ANGLE)
//        sweepAngle = typeArray.getFloat(R.styleable.SuperAppBG_sweep_angle, DEFAULT_SWEEP_ANGLE)
//        useCenter = typeArray.getBoolean(R.styleable.SuperAppBG_use_center, DEFAULT_USE_CENTER)
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
//        canvas!!.drawColor(resources.getColor(R.color.colorAccent))
        canvas!!.drawColor(ResourcesCompat.getColor(resources,R.color.white,null))
        Log.e("OvalShape","height $wHeight")
        Log.e("OvalShape","width $wWidth")
        val radius = wHeight
        val alpha = 225
        //bottom oval center
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.argb(alpha, 210, 48, 44)
        canvas.drawCircle(wWidth / 2 , -(radius / 2), radius, paint)
        }
    companion object {
        private const val DEFAULT_START_ANGLE : Float = 0f
        private const val DEFAULT_SWEEP_ANGLE : Float = 0f
        private const val DEFAULT_USE_CENTER : Boolean = true
    }
}
