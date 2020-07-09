package com.reaksmeyarun.pda.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.firebase.iid.FirebaseInstanceId
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.customView.LoadingView
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import kotlinx.android.synthetic.main.custom_toolbar.*


abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {
    val TAG_BASE = "BaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_toolbar)
        supportActionBar?.elevation = 0f
        btnBack?.setOnClickListener { onBackPressed() }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setBackgroundDrawable(getDrawable(R.drawable.def_activity_bg))
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun setActTitle(title : String){
        actTitle?.text = title
    }

    fun initFirebaseInstanceId() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            UserSession.getInstance(applicationContext).saveFirebaseToken(instanceIdResult.token)
        }
    }
    fun registerFirebaseToken(token :String){
//        TODO :
    }
    private val mLoadingViewParent: ViewGroup? = null
    private var loadingView: LoadingView? = null
    open fun showLoading(alpha: Float? = 0.3f) {
        loadingView = loadingView ?: LoadingView(this).show(this, alpha, mLoadingViewParent)
    }
    open fun hideLoading() {
        loadingView?.let{
            it.hide()
            loadingView = null
        }
    }
    open fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
    open fun showSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onResume() {
        super.onResume()
        hideSoftKeyboard()
    }

    open fun <T> startActivity(activity : Activity, classModel : Class<T>){
        activity.startActivity(Intent(activity.applicationContext, classModel))
        activity.finish()
    }
    open fun <T> startActivityForResult(activity : Activity, classModel : Class<T>, requestCode : Int){
        activity.startActivityForResult(Intent(activity.applicationContext, classModel), requestCode)
    }
    open fun <T> startActivityBundle(activity : Activity, classModel : Class<T>, bundle: Bundle){
        activity.startActivity(Intent(activity.applicationContext, classModel).putExtra(BUNDLE, bundle))
        activity.finish()
    }
    companion object{
        val BUNDLE = "putExtra"
    }
}