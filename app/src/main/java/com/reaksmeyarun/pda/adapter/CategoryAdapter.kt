package com.reaksmeyarun.pda.adapter

import android.content.Context
import android.view.View
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.utils.PopupMsg
import kotlinx.android.synthetic.main.item_category_layout.view.*

class CategoryAdapter(context : Context, val layoutId : Int) : BaseAdapter<CategoryModel>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.tvCategoryName.text = items[position].categoryInformation.categoryName
        holder.itemView.tvCategoryDescription.text = items[position].categoryInformation.categoryDescription
//        if(items[position].status == AppConstance.CAN_NOT_DELETE){
//            holder.itemView.btnEdit.visibility = View.GONE
//            holder.itemView.btnDelete.visibility = View.GONE
//        }else if (items[position].status == AppConstance.CAN_DELETE){
//            holder.itemView.btnEdit.visibility = View.VISIBLE
//            holder.itemView.btnDelete.visibility = View.VISIBLE
//        }
//        holder.itemView.itemViewLayout.setOnClickListener {
//            if(onClickRecyclerViewListener==null)
//                return@setOnClickListener
//            onClickRecyclerViewListener?.onClickListener(position, items[position])
//        }
        holder.itemView.btnEdit.setOnClickListener {
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
        fun onClickListener(pos : Int, data: CategoryModel)
        fun onEditListener(pos : Int, data: CategoryModel)
        fun onDeleteListener(pos : Int, data : CategoryModel)
    }
}