package com.reaksmeyarun.pda.adapter

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class TabLayoutAdapter : PagerAdapter() {
        var items: ArrayList<String> = ArrayList()
        fun addTab(title: String) {
            items.add(title)
            notifyDataSetChanged()
        }

        fun removeTab() {
            if (items.isNotEmpty()) {
                items.removeAt(items.size - 1)
                notifyDataSetChanged()
            }
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val tv = TextView(container.context)
            tv.text = getPageTitle(position)
            tv.gravity = Gravity.CENTER
//            tv.setTextAppearance(tv.context, R.style.TextAppearance_AppCompat_Title)
            container.addView(
                tv, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            return tv
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}