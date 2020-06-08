package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.model.ItemModel
import kotlinx.android.synthetic.main.item_item_layout.view.*

class ItemAdapter(context : Context, var layoutId : Int) : BaseAdapter<ItemModel>(context, layoutId) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.tvItemName.text = items[position].itemInformation.itemName
        holder.itemView.tvItemDescription.text = items[position].itemInformation.itemDescription

        holder.itemView.itemViewLayout.setOnClickListener {
            if(onClickRecyclerViewListener== null)
                return@setOnClickListener
            onClickRecyclerViewListener?.onClickListener(position, items[position])
        }
        holder.itemView.itemViewLayout.setOnClickListener {
            if(onClickRecyclerViewListener==null)
                return@setOnClickListener
            onClickRecyclerViewListener?.onEditListener(position, items[position])
        }
        holder.itemView.btnDelete.setOnClickListener {
            if(onClickRecyclerViewListener==null)
                return@setOnClickListener
            onClickRecyclerViewListener?.onDeleteListener(position, items[position])
        }
    }
    var onClickRecyclerViewListener : OnClickRecyclerViewListener?= null

    interface OnClickRecyclerViewListener {
        fun onClickListener(pos : Int, data: ItemModel)
        fun onEditListener(pos : Int, data: ItemModel)
        fun onDeleteListener(pos : Int, data : ItemModel)
    }
}