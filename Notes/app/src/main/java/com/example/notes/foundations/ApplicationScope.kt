package com.example.notes.foundations

import com.example.notes.ui.notes.INoteModel
import com.example.notes.ui.notes.NoteLocalModel
import com.example.notes.ui.tasks.ITaskModel
import com.example.notes.ui.tasks.TaskLocalModel
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope: Scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module() {
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}

object CreateActivityScope {
    val scope: Scope = Toothpick.openScope(this).apply {
        installModules(CreateActivityModule)
    }
}

object CreateActivityModule: Module() {
    init {
        bind(INoteModel::class.java).to(NoteLocalModel::class.java)
        bind(ITaskModel::class.java).to(TaskLocalModel::class.java)
//        bind(StateModel::class.java).toInstance(StateModel())
    }
}