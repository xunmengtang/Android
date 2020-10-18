package com.reaksmeyarun.pda.base

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.reaksmeyarun.pda.R

class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    init {

    }

    fun setAnim(){
        val anim = AnimationUtils.loadAnimation(
            itemView.context,
            R.anim.item_animation_fall_down
        )
        itemView.animation = anim
    }
    fun removeAnim(){
        itemView.animation = null
    }
}