package dev.jorik.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel :ViewModel() {
    private val database :TodoDao = App.db.todoDao()
    val flow : Flow<List<Todo>> = database.listen()

    fun toggle(todo: Todo){
        viewModelScope.launch {
            database.update(Todo(todo.id, todo.title, !todo.flag))
        }
    }

    fun add(name :String){
        viewModelScope.launch {
            database.add(Todo(0, name, false))
        }
    }

    fun edit(todo: Todo, name :String){
        viewModelScope.launch {
            database.update(Todo(todo.id, name, todo.flag))
        }
    }

    fun delete(todo :Todo){
        viewModelScope.launch {
            database.delete(todo)
        }
    }

}