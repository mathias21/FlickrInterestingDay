package com.batcuevasoft.flickrinterestingday.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.batcuevasoft.flickrinterestingday.R
import com.batcuevasoft.flickrinterestingday.ui.extensions.setupNoLimitsActionBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupDrawerLayout()

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when(destination.id) {
//                R.id.detail_fragment -> setupFullScreen()
//                else -> setupRegularScreen()
//            }
//        }
    }

//    private fun setupRegularScreen() {
//        setupNoLimitsActionBar(false, toolbar)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        toolbar.background.alpha = 100
//    }
//
//    private fun setupFullScreen() {
//        setupNoLimitsActionBar(true, toolbar)
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        toolbar.background.alpha = 0
//    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupDrawerLayout() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.today_fragment,
                R.id.past_fragment
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }
}