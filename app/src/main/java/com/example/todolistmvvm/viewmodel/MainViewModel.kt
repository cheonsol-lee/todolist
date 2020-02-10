package com.example.todolistmvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.example.todolistmvvm.model.Todo
import com.example.todolistmvvm.model.TodoDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    data class CheckedTodo(var id : Int, var title: String, var checked: Boolean = false)

    private val db = Room.databaseBuilder(
        context.applicationContext,
        TodoDB::class.java, "TodoDB"
    ).build()

    var newTodo: String? = null

    var todos: MutableLiveData<List<Todo>> = MutableLiveData()


    // Transformations.map(source, Function) : source에 이벤트발생할 때 Function수행되고 livedata를 리턴
    // map함수 : 값을 변형해서 새로운 리스트 생성
    val checkedTodos = Transformations.map(todos) { todos ->
        todos?.map { todo ->
            CheckedTodo(todo.id, todo.title, false)
        }
    }

    // Livedata postValue : Background에서 값을 전달할 때 사용
    fun load() = viewModelScope.launch(Dispatchers.IO) {
        todos.postValue(db.todoDao().getAll())
    }

    // 코루틴 사용(view모델스코프에서 IO스레드를 사용), 엘비스 연산자(?:)
    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        db.todoDao().insert(Todo(newTodo ?: ""))
        todos.postValue(db.todoDao().getAll())
    }

    // filter : { }의 내용이 True인 것만 필터링
    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("Test", "Removed: ${checkedTodos.value?.filter { it.checked }?.map { it.id }?.joinToString(",") ?: "NULL"}")
        checkedTodos.value?.filter { todo ->
            todo.checked
        }?.forEach { checkedTodo ->
            Log.d("Test", "${checkedTodo.id}")
            db.todoDao().delete(checkedTodo.id)
        }
        for (item in checkedTodos.value!!) {

        }

        todos.postValue(db.todoDao().getAll())
    }
}