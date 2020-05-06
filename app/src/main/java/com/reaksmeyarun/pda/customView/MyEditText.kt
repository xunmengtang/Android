package com.post.transfer.lanpost.mycustom

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.reaksmeyarun.pda.R


class MyEditText : androidx.appcompat.widget.AppCompatEditText {
    fun init() {
        //set font style
        val face = ResourcesCompat.getFont(context!!, R.font.khmer_os_battambang_regular)
        this.typeface = face
        //set text color
        setTextColor(ResourcesCompat.getColor(resources,R.color.colorEdtText,null))
        //set text size
        val desiredSp = resources.getDimension(R.dimen.defEditTextSize)
        val density = resources.displayMetrics.density
        setTextSize(TypedValue.COMPLEX_UNIT_SP, desiredSp / density)
        background = ResourcesCompat.getDrawable(resources,R.drawable.edt_background,null)
        setPadding(32,8,0,0)
        ////setBackgroundColor(ResourcesCompat.getColor(resources,android.R.color.transparent,null))
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