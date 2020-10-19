package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.ItemsAdapter
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.view.activity.SeeMoreActivity

class SeeMoreViewModel(var activity : SeeMoreActivity) : ViewModel(){

    fun initInformation(node : String?){
        if(node!=null){
            activity.binding.title.text == node
            when(node){
                AppConstance.CLOTHES ->{
                    activity.binding.banner.setImageResource(R.mipmap.clothes_banner)
                }
                AppConstance.SHOES ->{
                    activity.binding.banner.setImageResource(R.mipmap.shoes_banner)
                }
                AppConstance.PANTS ->{
                    activity.binding.banner.setImageResource(R.mipmap.pant_banner)
                }
                AppConstance.WATCHES ->{
                    activity.binding.banner.setImageResource(R.mipmap.watches_banner)
                }
            }
        }
    }

    fun requestItems(categoryId : String?, itemAdapter : ItemsAdapter){
        if(categoryId != null){
            val requestRecentlyItem = RequestItem(activity)
            requestRecentlyItem.onChildListener(object : FirebaseGetChildListener {
                override fun onCancelledListener(databaseError: DatabaseError) {
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot) {
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot) {
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot) {
                    val data = dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)
                    if(data!!.categoryID == categoryId){
                        itemAdapter.addItem(data)
                    }
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {

                }

            })
            requestRecentlyItem.execute()
        }
    }

    fun handleSeeMore(view : View){

    }
}