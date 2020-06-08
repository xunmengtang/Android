@file:Suppress("DEPRECATION")

package com.reaksmeyarun.pda.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reaksmeyarun.pda.R

private const val ARG_PARAM1 = "param1"

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    var list = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return Child.newInstance(
            list[position]
        )
    }

    override fun getCount(): Int {
        return list.size
    }

    class Child : Fragment() {
        private var param1: String? = ""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1)
            }
        }

        companion object {

            @JvmStatic
            fun newInstance(
                param1: String
            ) =
                Child().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(context).inflate(R.layout.p0210_home_main_layout, null, false)
        }
    }

}
