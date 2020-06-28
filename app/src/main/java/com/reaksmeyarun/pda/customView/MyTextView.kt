package com.reaksmeyarun.pda.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R


class MyTextView : androidx.appcompat.widget.AppCompatTextView {
    fun init(attrs: AttributeSet) {
        val density = resources.getDisplayMetrics().density
        val DEFAULT_TEXT_SIZE = resources.getDimension(R.dimen.defTextViewSize)
        val DEFAULT_TEXT_COLOR = ResourcesCompat.getColor(resources,R.color.colorDefText,null)
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        val textsize = typeArray.getDimension(R.styleable.MyTextView_android_textSize, DEFAULT_TEXT_SIZE)
        val textcolor = typeArray.getColor(R.styleable.MyTextView_android_textColor,DEFAULT_TEXT_COLOR)
        //set font style
        var face = ResourcesCompat.getFont(context, R.font.khmer_os_battambang_regular)
        typeface = face
        setTextColor(textcolor)
        setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize/density)
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs!!)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs!!)
    }

}