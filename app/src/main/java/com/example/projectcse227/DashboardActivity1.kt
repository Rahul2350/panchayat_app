package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.projectcse227.fragments.ContactUsFragment
import com.example.projectcse227.fragments.HomeFragment
import com.example.projectcse227.fragments.ProfileFragment
import com.example.projectcse227.fragments.QAFragment
import com.example.projectcse227.fragments.RulesFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DashboardActivity1 : AppCompatActivity() {
    private var navigationView: NavigationView? = null
    private var drawer: DrawerLayout? = null
    private var navHeader: View? = null
    private var imgNavHeaderBg: ImageView? = null
    private val imgProfile: ImageView? = null
    private val txtName: TextView? = null
    private val txtWebsite: TextView? = null
    private var toolbar: Toolbar? = null
    private lateinit var activityTitles: Array<String>
    private val shouldLoadHomeFragOnBackPress = true
    private var mHandler: Handler? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private var auth: FirebaseAuth? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        mHandler = Handler()
        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout?
        navigationView = findViewById<View>(R.id.nav_view) as NavigationView?
        navHeader = navigationView?.getHeaderView(0)
        imgNavHeaderBg = navHeader!!.findViewById<View>(R.id.img_header_bg) as ImageView
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles)
        auth = FirebaseAuth.getInstance()
        //get current user
        val user: FirebaseUser? = FirebaseAuth.getInstance().getCurrentUser()
        authListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                val user: FirebaseUser? = firebaseAuth.getCurrentUser()
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(Intent(getApplicationContext(), LoginActivity1::class.java))
                }
            }
        }
        loadNavHeader()
        setUpNavigationView()
        if (savedInstanceState == null) {
            navItemIndex = 0
            CURRENT_TAG = TAG_HOME
            loadHomeFragment()
        }
    }

    private fun loadNavHeader() {}
    private fun loadHomeFragment() {
        selectNavMenu()
        setToolbarTitle()
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer?.closeDrawers()
            return
        }
        val mPendingRunnable: Runnable = object : Runnable {
            override fun run() {
                // update the main content by replacing fragments
                val fragment: Fragment = homeFragment
                val fragmentTransaction: FragmentTransaction =
                    getSupportFragmentManager().beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG)
                fragmentTransaction.commitAllowingStateLoss()
            }
        }
        if (mPendingRunnable != null) {
            mHandler!!.post(mPendingRunnable)
        }
        drawer?.closeDrawers()
        invalidateOptionsMenu()
    }

    private val homeFragment: Fragment
        private get() = when (navItemIndex) {
            0 -> {
                // home
                HomeFragment()
            }

            1 -> {
                ProfileFragment()
            }

            2 -> {
                RulesFragment()
            }

            3 -> {
                ContactUsFragment()
            }

            4 -> {
                QAFragment()
            }

            else -> HomeFragment()
        }

    private fun setToolbarTitle() {
        getSupportActionBar()?.setTitle(activityTitles[navItemIndex])
    }

    private fun selectNavMenu() {
        navigationView?.getMenu()?.getItem(navItemIndex)?.setChecked(true)
    }

    private fun setUpNavigationView() {
        navigationView?.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            // This method will trigger on item Click of navigation menu
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.nav_home -> {
                        navItemIndex = 0
                        CURRENT_TAG = TAG_HOME
                    }

                    R.id.nav_profile -> {
                        navItemIndex = 1
                        CURRENT_TAG = TAG_PROFILE
                    }

                    R.id.nav_rules -> {
                        navItemIndex = 2
                        CURRENT_TAG = TAG_RULES
                    }

                    R.id.nav_contact_us -> {
                        navItemIndex = 3
                        CURRENT_TAG = TAG_CONTACT
                    }

                    R.id.nav_qa -> {
                        navItemIndex = 4
                        CURRENT_TAG = TAG_QA
                    }

                    R.id.nav_share -> {
                        val sendIntent = Intent()
                        sendIntent.setAction(Intent.ACTION_SEND)
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Share")
                        sendIntent.setType("text/plain")
                        Intent.createChooser(sendIntent, "Share via")
                        startActivity(sendIntent)
                        navItemIndex = 0
                    }

                    else -> navItemIndex = 0
                }
                if (menuItem.isChecked) {
                    menuItem.isChecked = false
                } else {
                    menuItem.isChecked = true
                }
                menuItem.isChecked = true
                loadHomeFragment()
                return true
            }
        })
        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawer?.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(GravityCompat.START) == true) {
            drawer!!.closeDrawers()
            return
        }
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0
                CURRENT_TAG = TAG_HOME
                loadHomeFragment()
                return
            }
        }
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show()
            signOut()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun signOut() {
        auth?.signOut()
    }

    override fun onStart() {
        super.onStart()
        authListener?.let { auth?.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth?.removeAuthStateListener(authListener!!)
        }
    }

    companion object {
        var navItemIndex = 0
        private const val TAG_HOME = "home"
        private const val TAG_PROFILE = "profile"
        private const val TAG_RULES = "rules"
        private const val TAG_CONTACT = "contact"
        private const val TAG_QA = "qa"
        private const val TAG_SHARE = "share"
        var CURRENT_TAG = TAG_HOME
    }
}