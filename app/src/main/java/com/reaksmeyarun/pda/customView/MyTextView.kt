package com.post.transfer.lanpost.mycustom

import android.content.Context
import android.content.res.ColorStateList
import android.text.BoringLayout
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R


class MyTextView : androidx.appcompat.widget.AppCompatTextView {
    fun init() {

        //set font style
        val face = ResourcesCompat.getFont(context!!, R.font.khmer_os_battambang_regular)
        this.typeface = face
        //set text color
        setTextColor(ResourcesCompat.getColor(resources, R.color.colorDefText, null))
        //set text size
        val desiredSp = resources.getDimension(R.dimen.defTextViewSize)
        val density = resources.displayMetrics.density
        setTextSize(TypedValue.COMPLEX_UNIT_SP, desiredSp / density)

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