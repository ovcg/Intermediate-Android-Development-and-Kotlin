package com.example.notes.ui.navigation

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notes.R
import com.example.notes.foundations.BaseFragment
import com.example.notes.ui.create.CreateActivity
import com.example.notes.ui.tasks.TaskListFragment

class NavigationActivity : AppCompatActivity(), BaseFragment.TouchActionDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tasks,
                R.id.navigation_notes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun goToCreateActivity(fragmentValue: String){
        startActivity(Intent(this, CreateActivity::class.java).apply {
            putExtra(fragmentValueKey, fragmentValue)
        })
    }

    override fun onAddButtonClicked(value: String) {
        goToCreateActivity(value)
    }

    companion object{
        const val fragmentValueKey = "fvk"
        const val fragmentValueNote = "fvn"
        const val fragmentValueTask = "fvt"
    }
}
