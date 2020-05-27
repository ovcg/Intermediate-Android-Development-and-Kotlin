package com.example.notes.ui.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.foundations.BaseFragment
import com.example.notes.models.Task
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TaskListView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    private lateinit var mAdapter: TasksAdapter
    private lateinit var touchActionDelegate: BaseFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(taDelegate: BaseFragment.TouchActionDelegate, daDelegate: TaskListViewContract) {
        setDelegate(taDelegate, daDelegate)
        setUpView()
    }

    private fun setDelegate(
        taDelegate: BaseFragment.TouchActionDelegate,
        daDelegate: TaskListViewContract
    ) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        mAdapter = TasksAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

    }

    fun updateList(list: MutableList<Task>) {
        mAdapter.updateList(list)
    }
}