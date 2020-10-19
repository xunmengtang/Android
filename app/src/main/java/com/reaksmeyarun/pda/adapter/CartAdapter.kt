package com.reaksmeyarun.pda.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.customView.MyTextView
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.model.CategoryModel
import kotlinx.android.synthetic.main.item_layout.view.*

class CartAdapter(context : Context, val layoutId : Int) : BaseAdapter<RequestCart.ResponseCart>(context, layoutId){

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

    }

}