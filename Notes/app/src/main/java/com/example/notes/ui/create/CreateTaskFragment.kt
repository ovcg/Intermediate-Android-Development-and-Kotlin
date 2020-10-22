package com.example.notes.ui.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.foundations.StateChangeTextWatcher
import com.example.notes.models.Task
import com.example.notes.models.Todo
import com.example.notes.ui.tasks.ITaskModel
import com.example.notes.ui.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val maxxTodoCount = 5

class CreateTaskFragment : Fragment() {

    @Inject
    lateinit var model: ITaskModel

    private var listener: OnFragmentInteractorListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createTaskView.taskEditText.addTextChangedListener(object : StateChangeTextWatcher() {

            override fun afterTextChanged(s: Editable?) {

                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                    removeTodoView(containerView.getChildAt(containerView.childCount - 1))
                }

                super.afterTextChanged(s)
            }

        })
    }

    private fun addTodoView() {

        if (canAddTodos()) {
            val view = (LayoutInflater.from(context)
                .inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView).apply {

                todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                    override fun afterTextChanged(s: Editable?) {

                        if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                            addTodoView()
                        } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                            removeTodoView(this@apply)

                            if (containerView.childCount == maxxTodoCount){
                                addTodoView()
                            }
                        }

                        super.afterTextChanged(s)
                    }
                })
            }

            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }

    private fun canAddTodos(): Boolean = containerView.childCount < maxxTodoCount + 1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractorListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractorListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun saveTask(callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            createTask().let { task ->
                task?.let {
                    model.add(it) { success ->
                        callback.invoke(success)
                    }
                }
            } ?: callback.invoke(false)
        }
    }

    private fun isTaskEmpty(): Boolean = createTaskView.taskEditText.editableText.isNullOrEmpty()

    fun createTask(): Task? {
        if (!isTaskEmpty()) {
            containerView.run {

                var taskField: String? = null
                val todoList: MutableList<Todo> = mutableListOf()
                for (i in 0 until containerView.childCount) {

                    if (i == 0) {
                        taskField = containerView.getChildAt(i).taskEditText.editableText?.toString()
                    } else {

                        if (!containerView.getChildAt(i).todoEditText.editableText.isNullOrEmpty()) {
                            todoList.add(
                                Todo(description = containerView.getChildAt(i).todoEditText.editableText.toString())
                            )
                        }
                    }
                }

                return taskField?.let {
                    Task(taskField, todoList)
                }
            }
        } else {
            return null
        }
    }

    interface OnFragmentInteractorListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance() = CreateTaskFragment()
    }
}