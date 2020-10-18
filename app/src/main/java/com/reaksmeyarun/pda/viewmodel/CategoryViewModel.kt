package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.constance.KeyConstance
import com.reaksmeyarun.pda.firebase.RequestCategory
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.RVItemClickCallback
import com.reaksmeyarun.pda.listener.RVItemDeleteCallback
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.view.activity.C0100CategoryActivity
import com.reaksmeyarun.pda.view.activity.C0200CategoryInformationActivity
import kotlinx.android.synthetic.main.activity_c0100_category.*

class CategoryViewModel(val activity : C0100CategoryActivity) : ViewModel() {
    private val TAG = "CategoryViewModel"
    private var categoryList = ArrayList<CategoryModel>()
    init {

    }
    fun bindingRcyCategory(){
        val categoryAdapter = CategoryAdapter(activity, R.layout.category_item_a0200_layout)
        activity.rvCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        activity.rvCategory.adapter = categoryAdapter
        categoryAdapter.apply {
            setItemClickCallBack(object : RVItemClickCallback<CategoryModel>{
                override fun onClick(item: CategoryModel, pos: Int) {

                }
            })
            setItemDeleteCallBack(object : RVItemDeleteCallback<CategoryModel>{
                override fun onDelete(item: CategoryModel, pos: Int) { /*TODO : */ }
            })
        }
//        requestCategory(categoryAdapter)
    }
    private fun requestCategory(categoryAdapter: CategoryAdapter){
        RequestCategory(activity).apply {
            onChildListener(object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) { /*TODO : */ }
                override fun onChildMoved(dataSnapshot: DataSnapshot) { /*TODO : */ }
                override fun onChildChanged(dataSnapshot: DataSnapshot){ /*TODO : */ }
                override fun onChildAdded(dataSnapshot: DataSnapshot) { dataSnapshot.getValue(CategoryModel::class.java)?.let { categoryAdapter.addItem(it) } }
                override fun onChildRemoved(dataSnapshot: DataSnapshot) { /*TODO : */ }
            })
        }.execute()
    }
    fun handleCreateCategory(view : View){
        activity.startActivity(Intent(activity, C0200CategoryInformationActivity::class.java).putExtra(KeyConstance.CREATE_CATEGORY, KeyConstance.CREATE_CATEGORY))
    }
}