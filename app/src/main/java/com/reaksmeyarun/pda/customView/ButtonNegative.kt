package com.reaksmeyarun.pda.customView

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R

class ButtonNegative : BaseButton {
    init {
        //set background
        background = ResourcesCompat.getDrawable(getResources(), R.drawable.btn_negative_background, null)
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}