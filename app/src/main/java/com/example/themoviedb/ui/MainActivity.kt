package com.example.themoviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.*
import androidx.navigation.ui.NavigationUI
import com.example.themoviedb.R

class MainActivity : AppCompatActivity() {
    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}