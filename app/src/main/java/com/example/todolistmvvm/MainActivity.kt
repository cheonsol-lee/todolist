package com.example.todolistmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.todolistmvvm.view.TodoListFragment

/**
 *
 * 추가기능 : readme 추가
 *
 * */

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 처음에 TodoList 화면 띄우기(fragment_todolist.xml)
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fragment_main, TodoListFragment())
        ft.commit()

    }

}
