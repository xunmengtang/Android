package com.reaksmeyarun.pda.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R


class MyEditText : androidx.appcompat.widget.AppCompatEditText {
    fun init(attrs: AttributeSet) {
        val desiredSp = resources.getDimension(R.dimen.defEditTextSize)
        val density = resources.displayMetrics.density
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditText)
        val textsize = typeArray.getDimension(R.styleable.MyEditText_android_textSize, desiredSp)
        val textcolor = typeArray.getColor(R.styleable.MyEditText_android_textColor,
            ResourcesCompat.getColor(resources,R.color.colorEdtText,null))
        val btnBG = typeArray.getResourceId(R.styleable.MyEditText_android_background,R.drawable.edt_background)
        //set font style
        typeface = ResourcesCompat.getFont(context, R.font.khmer_os_battambang_regular)
        setTextColor(textcolor)
        setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize/density)
        background = ResourcesCompat.getDrawable(resources,btnBG,null)
        setPadding(0,8,0,0)
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