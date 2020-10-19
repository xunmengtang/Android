package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import kotlinx.android.synthetic.main.cart_layout.view.*

class CartAdapter(context : Context, val layoutId : Int) : BaseAdapter<RequestCart.ResponseCart>(context, layoutId){
    var itemSelected = RequestCart.ResponseCart()
    var itemSelectedPos : Int ?= 0
    var addRemoveCallBack : AddRemoveCallBack ?= null
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemSelected = items[position]
            itemSelectedPos = position
            itemClickCallback?.onClick(items[position], position)
        }
        holder.itemView.btnAdd.setOnClickListener {
            items[position].quanities = itemSelected.quanities?:1.plus(1)
            holder.itemView.quanitites.text = items[position].quanities.toString()
            addRemoveCallBack?.add(items[position])
        }
        holder.itemView.btnRemove.setOnClickListener {
            if(items[position].quanities?:1 > 1){
                items[position].quanities = itemSelected.quanities?:1.minus(1)
                holder.itemView.quanitites.text = items[position].quanities.toString()
                addRemoveCallBack?.remove(items[position])
            }
        }
    }

    interface AddRemoveCallBack{
        fun add(item : RequestCart.ResponseCart)
        fun remove(item : RequestCart.ResponseCart)
    }
}