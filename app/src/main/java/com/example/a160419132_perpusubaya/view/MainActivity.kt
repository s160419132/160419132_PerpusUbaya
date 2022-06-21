package com.example.a160419132_perpusubaya.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.a160419132_perpusubaya.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{

    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController=(supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerlayout)
        NavigationUI.setupWithNavController(navView,navController)
        bottomNav.setupWithNavController(navController)
        setupNav()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerlayout)|| super.onSupportNavigateUp()
    }

    private fun setupNav() {
        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.itemHome -> visibleBottomNav()
                R.id.itemProfile-> visibleBottomNav()
                R.id.itemHistory-> visibleBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun visibleBottomNav() {
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomNav.visibility = View.GONE
    }
}