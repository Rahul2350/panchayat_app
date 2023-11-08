package com.example.projectcse227

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.projectcse227.Adapters.TabAdapter
import com.example.projectcse227.fragments.MahaYojanaFragment
import com.example.projectcse227.fragments.YojanaFragment
import com.google.android.material.tabs.TabLayout

class YojanaActivity1 : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var tabAdapter: TabAdapter? = null
    private lateinit var toolbar: Toolbar
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yojana)
        viewPager = findViewById<View>(R.id.viewPager) as ViewPager?
        tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout?
        tabAdapter = TabAdapter(getSupportFragmentManager())
        tabAdapter!!.addFragment(YojanaFragment(), "Yojana")
        tabAdapter!!.addFragment(MahaYojanaFragment(), "Maha-Yojana")
        viewPager?.setAdapter(tabAdapter)
        tabLayout?.setupWithViewPager(viewPager)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar!!.setNavigationOnClickListener { finish() }
    }
}