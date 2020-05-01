package com.post.transfer.lanpost.base.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.reaksmeyarun.pda.R

class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    init {
        val anim = AnimationUtils.loadAnimation(
            itemView.context,
            R.anim.item_animation_fall_down
        )
        itemView.animation = anim
    }
}