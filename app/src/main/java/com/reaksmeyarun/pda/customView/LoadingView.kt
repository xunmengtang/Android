package com.reaksmeyarun.pda.customView

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.reaksmeyarun.pda.R
import kotlinx.android.synthetic.main.view_loading.view.*

/****
 *
 * Created by ORN TONY on 01/01/19.
 *
 */

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    fun show(activity: Activity, alpha: Float? = null, parent: ViewGroup? = null): LoadingView {
        activity.hideSoftKeyboard()
        viewBackground.alpha = alpha ?: 0.3f

        val layoutParams = MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(0, 0, 0, 0)
        parent?.addView(this, layoutParams) ?: run {
            activity.window.decorView
                    .findViewById<ViewGroup>(android.R.id.content)
                    .addView(this, layoutParams)
        }

        return this
    }

    fun hide() {
        removeFromSuperView()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this)
    }
}

private fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

class CircleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    var color
        get() = paint.color
        set(value) {
            paint.color = value
            invalidate()
        }

    private val paint = Paint()

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircleView)
            color = typedArray.getColor(R.styleable.CircleView_color, Color.WHITE)
            typedArray.recycle()
        } ?: run {
            color = Color.WHITE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        canvas.drawCircle((canvas.width / 2).toFloat(), (canvas.height / 2).toFloat(), (canvas.width / 2).toFloat(), paint)
    }
}

fun View.removeFromSuperView() {
    (parent as ViewGroup).removeView(this)
}