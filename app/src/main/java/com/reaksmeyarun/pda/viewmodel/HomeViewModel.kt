package com.reaksmeyarun.pda.viewmodel

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.adapter.ItemsAdapter
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.view.activity.CartActivity
import com.reaksmeyarun.pda.view.activity.DetailActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.SeeMoreActivity

class HomeViewModel(var activity : P0200HomeActivity) : ViewModel() {

    private val TAG = "HomeViewModel"
    fun initCategory() : ArrayList<String>{
        return ArrayList<String>().apply {
            this.add("Watch")
            this.add("Clothes")
            this.add("Shoes")
            this.add("Pants")
        }
    }

    fun requestClothes(rvAdaper : ItemsAdapter){
        val requestRecentlyItem = RequestItem(activity)
        requestRecentlyItem.onChildListener(object : FirebaseGetChildListener{
            override fun onCancelledListener(databaseError: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildChanged(dataSnapshot: DataSnapshot) {

            }

            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)
                if(data != null){
                    if(data.categoryID == FirebaseConstance.ID_CLOTHES){
                        rvAdaper.addItem(data)
                    }
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

        })
        requestRecentlyItem.execute()
    }
    fun requestShoes(rvAdaper : ItemsAdapter){
        val requestRecentlyItem = RequestItem(activity)
        requestRecentlyItem.onChildListener(object : FirebaseGetChildListener{
            override fun onCancelledListener(databaseError: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildChanged(dataSnapshot: DataSnapshot) {

            }

            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)
                if(data != null){
                    if(data.categoryID == FirebaseConstance.ID_SHOES){
                        rvAdaper.addItem(data)
                    }
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

        })
        requestRecentlyItem.execute()
    }
    fun requestWatch(rvAdaper : ItemsAdapter){
        val requestRecentlyItem = RequestItem(activity)
        requestRecentlyItem.onChildListener(object :FirebaseGetChildListener{
            override fun onCancelledListener(databaseError: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildChanged(dataSnapshot: DataSnapshot) {

            }

            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)
                if(data!!.categoryID == FirebaseConstance.ID_WATCH){
                    rvAdaper.addItem(data)
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

        })
        requestRecentlyItem.execute()
    }
    fun requestPants(rvAdaper : ItemsAdapter){
        val requestRecentlyItem = RequestItem(activity)
        requestRecentlyItem.onChildListener(object :FirebaseGetChildListener{
            override fun onCancelledListener(databaseError: DatabaseError) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)
                if(data!!.categoryID == FirebaseConstance.ID_PANTS){
                    rvAdaper.addItem(data)
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

        })
        requestRecentlyItem.execute()
    }

    fun handleSeeMoreWatches(view : View){
        intentTo(AppConstance.WATCHES, FirebaseConstance.ID_WATCH)
    }
    fun handleSeeMoreShoes(view : View){
        intentTo(AppConstance.SHOES, FirebaseConstance.ID_SHOES)
    }
    fun handleSeeMorePants(view : View){
        intentTo(AppConstance.PANTS, FirebaseConstance.ID_PANTS)
    }
    fun handleSeeMoreClothes(view : View){
        intentTo(AppConstance.CLOTHES, FirebaseConstance.ID_CLOTHES)
    }

    fun intentTo(node : String?, data : String?){
        if(data != null && node != null){
            val intent = Intent(activity, SeeMoreActivity::class.java)
            intent.putExtra(AppConstance.CATEGORY_NODE, node)
            intent.putExtra(AppConstance.SEE_MORE, data)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            activity.startActivity(intent)
        }
    }

    fun handleCart(view : View){
        val intent = Intent(activity, CartActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        activity.startActivity(intent)
    }
}