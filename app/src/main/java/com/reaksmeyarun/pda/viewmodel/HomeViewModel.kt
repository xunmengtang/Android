package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.adapter.ItemsAdapter
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity

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
                        rvAdaper.addItem(dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)!!)
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
                        rvAdaper.addItem(dataSnapshot.getValue(RequestItem.ResponseItem().javaClass)!!)
                    }
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

        })
        requestRecentlyItem.execute()
    }
}