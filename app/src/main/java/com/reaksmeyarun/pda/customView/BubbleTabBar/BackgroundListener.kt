@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.reaksmeyarun.pda.customView.BubbleTabBar

import android.os.Build.VERSION
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.reaksmeyarun.pda.customView.BubbleTabBar.base.BaseDrawable
import java.util.*

class BackgroundListener(private var colorBoxes :  IntArray,
//                        private var imageView : ImageView?,
                        var vp: ViewPager? ) : ViewPager.OnPageChangeListener{

    private var position: FloatArray ?= null
    private var state = 0
    init {
        setPosition()
    }
    private fun setPosition() {
        position = FloatArray(colorBoxes.size)
        Arrays.fill(position, 1.0f)
        position!![0] = 0.0f
    }

    private fun updatePosition(pos: Int) {
        pos.let {
            for (i in 0 until pos) {
                position?.set(i, 0.0f)
            }
        }
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        if (VERSION.SDK_INT >= 16) {
            if (position < vp!!.adapter!!.count - 1 && position < colorBoxes.size - 2
            ) {
                this.position?.set(position, (-1 * position).toFloat())
                this.position?.set(position + 1, 1.0f - positionOffset)
            }
//            imageView!!.background = BaseDrawable()
//                .drawable(colorBoxes, this.position)
        }
    }

    override fun onPageSelected(position: Int) {
        updatePosition(position)
        if (VERSION.SDK_INT >= 16 && state == 0) {
            if (position < vp!!.adapter!!.count - 1 && position < colorBoxes.size - 2
            ) {
                this.position?.set(position, (-1 * position).toFloat())
                this.position?.set(position + 1, 1.0f)
            }
//            imageView!!.background = BaseDrawable()
//                .drawable(colorBoxes, this.position)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        this.state = state
    }
}