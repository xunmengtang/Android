package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.post.transfer.lanpost.base.adapter.BaseAdapter
import com.post.transfer.lanpost.base.adapter.BaseViewHolder
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.SettingModel
import kotlinx.android.synthetic.main.item_setting_layout.view.*

class SettingAdapter(context: Context,val layoutId : Int) : BaseAdapter<SettingModel>(context, layoutId) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.linearViewLayout.setOnClickListener {
            if(onClickListener==null)
                return@setOnClickListener
            onClickListener?.onClick(position)
        }
        holder.itemView.label.text = items[position].title
    }

    var onClickListener : OnClickListener?= null
}