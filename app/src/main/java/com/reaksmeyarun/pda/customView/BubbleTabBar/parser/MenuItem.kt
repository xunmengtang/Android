package com.reaksmeyarun.pda.customView.BubbleTabBar.parser

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes

data class MenuItem(
    val id: Int,
    val title: CharSequence,
    @DrawableRes val icon: Int,
    val enabled: Boolean,
    @ColorInt val iconColor: Int,
    val checked: Boolean = false
) {

    @FontRes
    var custom_font: Int = 0
    var horizontal_padding: Float = 0f
    var vertical_padding: Float = 0f
    var icon_padding: Float = 0f
    var icon_size: Float = 0f
    var title_size: Float = 0f
    var disabled_icon_color: Int = Color.GRAY
}