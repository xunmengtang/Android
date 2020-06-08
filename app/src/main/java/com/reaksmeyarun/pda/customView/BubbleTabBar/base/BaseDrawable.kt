package com.reaksmeyarun.pda.customView.BubbleTabBar.base

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable.ShaderFactory
import android.graphics.drawable.shapes.RectShape
import android.widget.TextView
import com.reaksmeyarun.pda.customView.BubbleTabBar.AngleCoordinate
import com.reaksmeyarun.pda.customView.BubbleTabBar.GradientAngle

class BaseDrawable {

    fun drawable(
        colorBoxes: IntArray?,
        position: FloatArray?,
        gradientAngle: GradientAngle?
    ): Drawable? {
        val shaderFactory : ShaderFactory = object : ShaderFactory() {
            override fun resize(width: Int, height: Int) : Shader {
                val ac = AngleCoordinate()
                    .getAngleCoordinate(gradientAngle, width.toFloat(), height.toFloat())
                return LinearGradient(
                    ac.x1, ac.y1, ac.x2, ac.y2,
                    colorBoxes!!,
                    position,
                    Shader.TileMode.REPEAT
                )
            }
        }
        val paint = PaintDrawable()
        paint.shape = RectShape()
        paint.shaderFactory = shaderFactory
        return paint
    }

    fun drawable(colorBoxes: IntArray?, position: FloatArray?): Drawable? {
        return drawable(colorBoxes, position,
            GradientAngle.LEFT_TOP_TO_RIGHT_BOTTOM
        )
    }

    fun drawable(colorBoxes: IntArray, gradientAngle: GradientAngle?): Drawable? {
        val position = FloatArray(colorBoxes.size)
        for (i in position.indices) {
            position[i] = i * 1.toFloat() / (position.size - 1)
        }
        position[position.size - 1] = 1F
        return drawable(colorBoxes, position, gradientAngle)
    }

    fun drawable(colorBoxes: IntArray?): Drawable? {
        return colorBoxes?.let { drawable(it,
            GradientAngle.LEFT_TOP_TO_RIGHT_BOTTOM
        ) }
    }

    //for texts

    //for texts
    private fun setGradient(
        textView: TextView,
        colorBoxes: IntArray?,
        position: FloatArray?,
        gradientAngle: GradientAngle?
    ) {
        val ac: AngleCoordinate = AngleCoordinate()
            .getAngleCoordinate(
            gradientAngle,
            textView.width.toFloat(),
            textView.height.toFloat()
        )
        val linearGradient = LinearGradient(
            ac.x1, ac.y1, ac.x2, ac.y2,
            colorBoxes!!,
            position,
            Shader.TileMode.REPEAT
        )
        textView.invalidate()
        textView.paint.shader = linearGradient
    }

    private fun setGradient(
        textView: TextView,
        colorBoxes: IntArray,
        gradientAngle: GradientAngle?
    ) {
        val position = FloatArray(colorBoxes.size)
        for (i in position.indices) {
            position[i] = i * 1.toFloat() / (position.size - 1)
        }
        position[position.size - 1] = 1F
        setGradient(textView, colorBoxes, position, gradientAngle)
    }

    fun setGradient(
        textView: TextView,
        colorBoxes: IntArray?,
        position: FloatArray?
    ) {
        setGradient(textView, colorBoxes, position,
            GradientAngle.LEFT_BOTTOM_TO_RIGHT_TOP
        )
    }

    fun setGradient(textView: TextView?, colorBoxes: IntArray?) {
        setGradient(textView!!, colorBoxes!!,
            GradientAngle.LEFT_BOTTOM_TO_RIGHT_TOP
        )
    }
}