package com.example.notes.ui.create

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.foundations.CreateActivityScope
import com.example.notes.models.Task
import com.example.notes.ui.navigation.NavigationActivity
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import toothpick.Toothpick

class CreateActivity : AppCompatActivity(), CreateTaskFragment.OnFragmentInteractorListener,
    CreateNoteFragment.OnFragmentInteractorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        Toothpick.inject(this, CreateActivityScope.scope)

        supportActionBar?.title = ""

        intent.getStringExtra(NavigationActivity.fragmentValueKey).run {
            if (this == NavigationActivity.fragmentValueTask) {
                createFragment(CreateTaskFragment.newInstance())
            } else if (this == NavigationActivity.fragmentValueNote) {
                createFragment(CreateNoteFragment.newInstance())
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
                supportFragmentManager.findFragmentById(R.id.fragmentHolder)?.run {

                    if (this is CreateTaskFragment) {
                        val task = this.createTask()
                        GlobalScope.launch {
                            withContext(Dispatchers.IO) {
                                try {
                                    val tasks = Paper.book().read("tasks", HashMap<String, Task>())
                                    task?.let {
                                        tasks[it.uid.toString()] = it
                                    }
                                    Paper.book().write("tasks", tasks)
                                    withContext(Dispatchers.Main){
                                        Toast.makeText(this@CreateActivity, "Task salva!", Toast.LENGTH_SHORT).show()
                                        this@CreateActivity.finish()
                                    }
                                } catch (e: Exception) {
                                    Log.e("Erro", e.message!!)
                                }
                            }
                        }
                        /*this.saveTask { success ->
                            if (success) {
                                this@CreateActivity.supportFinishAfterTransition()
                            } else {
                                Toast.makeText(this@CreateActivity, getString(R.string.toast_error_saving), Toast.LENGTH_SHORT).show()
                            }
                        }*/
                    } /*else if (this is CreateNoteFragment) {
//                        this.saveNote { success ->
//                            if (success) {
//                                this@CreateActivity.supportFinishAfterTransition()
//                            } else {
//                                Toast.makeText(this@CreateActivity, getString(R.string.toast_error_saving), Toast.LENGTH_SHORT).show()
//                            }
//                        }
                    }*/

                }
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
