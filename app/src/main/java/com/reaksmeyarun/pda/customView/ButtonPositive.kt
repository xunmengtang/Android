package com.post.transfer.lanpost.mycustom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R

class ButtonPositive : androidx.appcompat.widget.AppCompatButton {
    fun init() {

        //set font style
        val face = ResourcesCompat.getFont(context!!, R.font.khmer_os_battambang_regular)
        this.typeface = face
        //set text color
        setTextColor(ResourcesCompat.getColor(resources, R.color.colorBtnText, null))
        //set text size
        val desiredSp = resources.getDimension(R.dimen.defButtonTextSize)
        setSupportAllCaps(false)
        val density = resources.getDisplayMetrics().density
        setTextSize(TypedValue.COMPLEX_UNIT_SP, desiredSp / density)
        //set background
        background =
            ResourcesCompat.getDrawable(getResources(), R.drawable.btn_positive_background, null)

    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

}