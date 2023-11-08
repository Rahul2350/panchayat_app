 package com.example.projectcse227.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mTitleList = ArrayList<String>()

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(i: Int): Fragment {
        return mFragmentList[i]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
}
