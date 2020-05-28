package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.SettingModel
import kotlinx.android.synthetic.main.item_setting_layout.view.*

class SettingAdapter(context: Context,var layoutId : Int) : BaseAdapter<SettingModel>(context, layoutId) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.linearViewLayout.setOnClickListener {
            if(onClickListener==null)
                return@setOnClickListener
            onClickListener?.onClick(position)
        }
        holder.itemView.label.text = items[position].title
        holder.itemView.icon.background = items[position].iconInt?.let { context.getDrawable(it) }
    }

    var onClickListener : OnClickListener?= null
}