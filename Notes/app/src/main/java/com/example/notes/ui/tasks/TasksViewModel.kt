package com.example.notes.ui.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.application.NoteApplication
import com.example.notes.database.RoomDBClient
import com.example.notes.foundations.ApplicationScope
import com.example.notes.models.Task
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TasksViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var taskModel: ITaskModel

    private val _taskListLiveData = MutableLiveData<MutableList<Task>>()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData(){

        GlobalScope.launch {
            withContext(Dispatchers.IO){
                val tasks = Paper.book().read("tasks", HashMap<String, Task>())
                withContext(Dispatchers.Main){
                    _taskListLiveData.postValue(tasks.values.toMutableList())
                }
            }
        }
//        taskModel.retrieve {nullableList ->
//            nullableList?.let {
//                _taskListLiveData.postValue(it.toMutableList())
//            }
//        }

    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

}