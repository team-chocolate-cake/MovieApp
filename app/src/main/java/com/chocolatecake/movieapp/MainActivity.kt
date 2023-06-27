package com.chocolatecake.movieapp

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolatecake.movieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeAppTheme()
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)

        val reselectedListener = CustomOnNavigationItemReselectedListener(navController)
        binding.bottomNavigation.setOnNavigationItemReselectedListener(reselectedListener)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.chocolatecake.ui.home.R.id.homeFragment,
                com.chocolatecake.ui.home.R.id.searchFragment,
                com.chocolatecake.ui.home.R.id.tvFragment,
                com.chocolatecake.ui.home.R.id.profileFragment -> showBottomNav()

                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.apply {
            visibility = View.VISIBLE
            animate()
                .translationY(0f)
                .setDuration(300)
                .start()
        }
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.apply {
            animate()
                .translationY(height.toFloat())
                .setDuration(300)
                .withEndAction { visibility = View.GONE }
                .start()
        }
    }

    private fun changeAppTheme() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val savedThemeState = sharedPreferences.getBoolean(PREF_THEME_STATE, false)
        if (savedThemeState) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    inner class CustomOnNavigationItemReselectedListener(private val navController: NavController) :
        BottomNavigationView.OnNavigationItemReselectedListener {

        override fun onNavigationItemReselected(item: MenuItem) {
            // Do nothing when the same item is reselected
        }
    }

    companion object {
        private const val PREF_THEME_STATE = "theme_state"
    }
}
