package com.reaksmeyarun.pda.adapter

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.utils.ConvertUtils
import com.reaksmeyarun.pda.view.activity.DetailActivity
import kotlinx.android.synthetic.main.item_layout.view.*
 class ItemsAdapter(context : Context, val layoutId : Int) : BaseAdapter<RequestItem.ResponseItem>(context, layoutId){

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.title.isSelected = true
        holder.itemView.title.text = items[position].itemName
        holder.itemView.price.text = "$ "+items[position].price.toString()
        Glide.with(context).load(items[position].image?.url).placeholder(R.color.space_gray).into(holder.itemView.icon)
        holder.itemView.setOnClickListener {

        }
    }

}