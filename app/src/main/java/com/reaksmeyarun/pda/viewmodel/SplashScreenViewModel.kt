package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.dataObject.Category
import com.reaksmeyarun.pda.firebaseRepo.RequestCategory
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.utils.DataSnapShotConvertUtils
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import com.reaksmeyarun.pda.view.activity.Z0100SplashScreenActivity


class SplashScreenViewModel (var activity : Z0100SplashScreenActivity) : ViewModel(){
    private val TAG = "SplashScreenViewModel"
    init {
        requestCategory()
    }

    fun requestCategory(){
        val requestCategory = RequestCategory(activity)
        requestCategory.onSingleValueListener(object : FirebaseGetListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val arrayOfCategory =
                    DataSnapShotConvertUtils.dataSnapShotToArrayList(
                        RequestCategory.ResponseCategory().javaClass,
                        dataSnapshot
                    )
                for (i in arrayOfCategory) {
                    when (i.id) {
                        FirebaseConstance.ID_CLOTHES -> {
                            Category.category.add(i)
                        }
                        FirebaseConstance.ID_PANTS -> {
                            Category.pants.add(i)
                        }
                        FirebaseConstance.ID_SHOES -> {
                            Category.shoes.add(i)
                        }
                        FirebaseConstance.ID_WATCH -> {
                            Category.watches.add(i)
                        }
                    }
                }
                FirebaseAuth.getInstance().addAuthStateListener {
                    if (FirebaseAuth.getInstance().currentUser == null) {
                        activity.startActivity(Intent(activity, Z0200SignInActivity::class.java))
                    } else {
                        activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                    }
                }
            }

            override fun onCancelListener(databaseError: DatabaseError) {
                //DO nothing
            }
        })
        requestCategory.execute()
    }
}