package com.trippyheads.covires.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.trippyheads.covires.R
import com.trippyheads.covires.databinding.ActivityLauncherBinding
import timber.log.Timber

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.feedbackFragment,R.id.aboutFragment))

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bttmNavViewItems.setupWithNavController(navController)

        Timber.d("Hello Guys, this is starting point of CoviRes App")
    }

}