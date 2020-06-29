package com.example.notes.ui.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.ui.navigation.NavigationActivity

class CreateActivity : AppCompatActivity(), CreateTaskFragment.OnFragmentInteractorListener,
    CreateNoteFragment.OnFragmentInteractorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar?.title = ""

        intent.getStringExtra(NavigationActivity.fragmentValueKey).run {
            if (this == NavigationActivity.fragmentValueTask) {
                createFragment(CreateTaskFragment())
            } else if (this == NavigationActivity.fragmentValueNote) {
                createFragment(CreateTaskFragment())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.saveItem -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun onFragmentInteraction() {

    }
}
