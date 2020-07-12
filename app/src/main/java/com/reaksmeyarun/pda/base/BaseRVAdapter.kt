package com.reaksmeyarun.pda.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reaksmeyarun.pda.listener.RVItemClickCallback
import com.reaksmeyarun.pda.listener.RVItemDeleteCallback

abstract class BaseAdapter<Model>(context: Context,layoutId: Int) : RecyclerView.Adapter<BaseViewHolder>(){
    var context = context
    private val layoutId = layoutId
    var items = ArrayList<Model>()
    private var itemClickCallback : RVItemClickCallback<Model>? = null
    private var itemDeleteCallback : RVItemDeleteCallback<Model>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(context).inflate(layoutId,parent,false))
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun setItemClickCallBack(callback: RVItemClickCallback<Model>){
        itemClickCallback = callback
    }
    fun setItemDeleteCallBack(callback: RVItemDeleteCallback<Model>){
        itemDeleteCallback = callback
    }
    fun addItem(data : Model){
        items.add(data)
        notifyDataSetChanged()
    }
    fun addItem(arrayList : ArrayList<Model>){
        for(data in arrayList){
            items.add(data)
        }
        notifyDataSetChanged()
    }
    fun addNewItem(arrayList : ArrayList<Model>){
        items.clear()
        items.addAll(arrayList)
        notifyItemRangeRemoved(0, items.size)
        notifyDataSetChanged()
    }
    fun removeItem(data : Model){
        val pos = items.indexOf(data)
        items.removeAt(pos)
        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }
    fun removeItem(pos : Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }
    fun clearAll(){
        items.clear()
        notifyItemRangeRemoved(0, items.size)
        notifyDataSetChanged()
    }
    fun replace(index : Int, data : Model){
        data?.let {
            items[index] = it
            notifyItemChanged(index)
        }
    }
}