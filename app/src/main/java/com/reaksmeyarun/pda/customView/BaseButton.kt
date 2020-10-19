package com.reaksmeyarun.pda.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R

open class BaseButton: androidx.appcompat.widget.AppCompatButton {
    fun init(attrs: AttributeSet) {
        val desiredSp = resources.getDimension(R.dimen.defButtonTextSize)
        val density = resources.displayMetrics.density
        val DEFAULT_TEXT_COLOR = ResourcesCompat.getColor(resources,R.color.colorBtnText,null)
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseButton)
        val textsize = typeArray.getDimension(R.styleable.BaseButton_android_textSize, desiredSp)
        val textcolor = typeArray.getColor(R.styleable.BaseButton_android_textColor,DEFAULT_TEXT_COLOR)
        //set font style
        val face = ResourcesCompat.getFont(context, R.font.khmer_os_battambang_regular)
        typeface = face
        setSupportAllCaps(false)
        setTextColor(textcolor)
        setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize/density)
        setPadding(0,1,0,0)
    }
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init(attrs!!)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        init(attrs!!)
    }
}