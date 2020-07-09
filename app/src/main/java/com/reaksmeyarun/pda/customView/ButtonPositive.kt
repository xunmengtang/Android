package com.reaksmeyarun.pda.customView

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R

class ButtonPositive : BaseButton {
    init {
        //set background
        background = ResourcesCompat.getDrawable(resources, R.drawable.btn_positive_background, null)
        setTextColor(ResourcesCompat.getColor(resources,R.color.colorBtnText,null))
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

}