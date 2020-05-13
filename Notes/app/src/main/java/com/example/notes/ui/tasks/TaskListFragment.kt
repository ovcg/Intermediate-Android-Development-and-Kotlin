package com.example.notes.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.models.Task
import com.example.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_tasks.*

class TaskListFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAdapter = TasksAdapter(
            mutableListOf(
                Task("Task One", mutableListOf(
                    Todo("Test One", true),
                    Todo("Test Two")
                )),
                Task("Task Two")
            )
        )
        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }
}
