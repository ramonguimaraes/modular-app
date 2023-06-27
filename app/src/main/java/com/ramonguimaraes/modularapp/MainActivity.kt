package com.ramonguimaraes.modularapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ramonguimaraes.modularapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setUpToolbar()
        setUpNavigationView()
    }

    private fun setUpToolbar() {
        binding.toolbar.setupWithNavController(provideNavController(), provideAppBarConfiguration())
    }

    private fun setUpNavigationView() {
        binding.navView.setupWithNavController(provideNavController())
    }

    private fun provideAppBarConfiguration(): AppBarConfiguration {
        val topLevelDestinations = setOf(
            com.ramonguimaraes.home.R.id.homeFragment,
            com.ramonguimaraes.products.R.id.productsFragment,
            com.ramonguimaraes.clients.R.id.clientsFragment
        )
        return AppBarConfiguration.Builder(topLevelDestinations)
            .setOpenableLayout(binding.drawerLayout).build()
    }

    private fun provideNavController(): NavController {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id)) as NavHostFragment
        return navHostFragment.navController
    }
}
