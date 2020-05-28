package com.reaksmeyarun.pda.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Model>(context: Context,layoutId: Int) : RecyclerView.Adapter<BaseViewHolder>(){
    var context = context
    private val layoutId = layoutId
    var items = ArrayList<Model>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(context).inflate(layoutId,parent,false))
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun addItem(data : Model){
        items.add(data)
        notifyDataSetChanged()
    }
    fun addItem(datas : ArrayList<Model>){
        for(data in datas){
            items.add(data)
        }
        notifyDataSetChanged()
    }
    fun removeItem(data : Model){
        val pos = items.indexOf(data)
        items.removeAt(pos)
        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }
    fun clearAll(){
        items.clear()
        notifyItemRangeRemoved(0,items.size)
        notifyDataSetChanged()
    }
}