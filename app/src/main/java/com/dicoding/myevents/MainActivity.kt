package com.dicoding.myevents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.dicoding.myevents.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main_activity) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_upcoming -> {
                    navController.navigate(R.id.navigation_upcoming)
                    true
                }
                R.id.navigation_finished -> {
                    navController.navigate(R.id.navigation_finished)
                    true
                }
                else -> false
            }
        }
    }
}
