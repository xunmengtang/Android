package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import kotlinx.android.synthetic.main.cart_layout.view.*

class CartAdapter(context : Context, val layoutId : Int) : BaseAdapter<RequestCart.ResponseCart>(context, layoutId){
    var itemSelected = RequestCart.ResponseCart()
    var itemSelectedPos : Int ?= 0
    var arrayListOfTotal = ArrayList<Double>()
    private var addRemoveCallBack : AddRemoveCallBack ?= null
    private var removeCallBack : RemoveItem ?= null
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        arrayListOfTotal.add(items[position].quanities!!.toDouble() * items[position].item?.price!!.toDouble())
        Glide.with(context).load(items[position].item?.image?.url).into(holder.itemView.iconCart)
        holder.itemView.quanitites.text = items[position].quanities.toString()
        holder.itemView.itemName.text = items[position].item?.itemName
        holder.itemView.itemPrice.text = "$ ${(items[position].quanities!!.toFloat() * items[position].item?.price!!.toFloat())}"

        holder.itemView.setOnClickListener {
            itemSelected = items[position]
            itemSelectedPos = position
            itemClickCallback?.onClick(items[position], position)
        }

        holder.itemView.btnAdd.setOnClickListener {
            items[position].quanities = items[position].quanities!!.plus(1)
            holder.itemView.quanitites.text = items[position].quanities.toString()
            arrayListOfTotal[position] = items[position].quanities!!.toDouble() * items[position].item?.price!!.toDouble()
            addRemoveCallBack?.add(items[position])
        }

        holder.itemView.btnRemove.setOnClickListener {
            if(items[position].quanities?:1 > 1){
                items[position].quanities = items[position].quanities!!.minus(1)
                holder.itemView.quanitites.text = items[position].quanities.toString()
                arrayListOfTotal[position] = items[position].quanities!!.toDouble() * items[position].item?.price!!.toDouble()
                addRemoveCallBack?.remove(items[position])
            }
        }

        holder.itemView.btnDelete.setOnClickListener{
            itemSelected = items[position]
            removeCallBack?.onDelete(items[position], position)
        }

    }

    fun setAddRemoveCallBack(listener : AddRemoveCallBack){
        this.addRemoveCallBack = listener
    }

    fun setRemoveItemCallBack(listener : RemoveItem){
        this.removeCallBack = listener
    }

    interface RemoveItem{
        fun onDelete(item : RequestCart.ResponseCart, position: Int)
    }

    interface AddRemoveCallBack{
        fun add(item : RequestCart.ResponseCart)
        fun remove(item : RequestCart.ResponseCart)
    }
}