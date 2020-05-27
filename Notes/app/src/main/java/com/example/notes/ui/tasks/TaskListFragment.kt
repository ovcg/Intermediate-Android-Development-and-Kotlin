package com.example.notes.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.foundations.BaseFragment

class TaskListFragment : BaseFragment() {

    private lateinit var taskViewModel: TasksViewModel
    private lateinit var contentView: TaskListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false).apply {
            contentView = this as TaskListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setContentView()

    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, taskViewModel)
    }

    private fun bindViewModel() {
        taskViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        taskViewModel.taskListLiveData.observe(viewLifecycleOwner, Observer {
            contentView.updateList(it)
        })
    }
}
