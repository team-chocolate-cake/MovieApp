package com.chocolatecake.movieapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolatecake.local.PreferenceStorage
import com.chocolatecake.movieapp.databinding.ActivityMainBinding
import com.chocolatecake.ui.LoginFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        handleIsAuthorized(navController)
    }

    private fun handleIsAuthorized(navController: NavController) {
        if (!preferenceStorage.sessionId.isNullOrBlank()) {
            navController
                .navigate(LoginFragmentDirections.actionLoginFragmentToHomeNavGraph())
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
    inner class CustomOnNavigationItemReselectedListener(private val navController: NavController) :
        BottomNavigationView.OnNavigationItemReselectedListener {

        override fun onNavigationItemReselected(item: MenuItem) {
            // Do nothing when the same item is reselected
        }
    }
}
