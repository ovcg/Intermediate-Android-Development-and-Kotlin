package com.example.notes.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.models.Task
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TasksViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var taskModel: ITaskModel

    private val _taskListLiveData = MutableLiveData<MutableList<Task>>()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {

        val scope = Toothpick.openScope(this)
        scope.installModules(object : Module(){
            init {
                bind(ITaskModel::class.java).toInstance(TaskLocalModel())
            }
        })
        Toothpick.inject(this, scope)
        _taskListLiveData.postValue(taskModel.retrieve().toMutableList())

    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

}