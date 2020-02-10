package com.example.todolistmvvm.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.todolistmvvm.FragmentChange
import com.example.todolistmvvm.MainRvAdapter
import com.example.todolistmvvm.R
import com.example.todolistmvvm.databinding.FragmentTodolistBinding
import com.example.todolistmvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_todolist.*
import kotlinx.android.synthetic.main.fragment_update.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : Fragment() {
    //by키워드 : 추상메서드들을 명시된 객체로 구현을 위임
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentTodolistBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_todolist, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView
        val adapter = MainRvAdapter()
        adapter.itemClick = { checkedTodo ->
            Toast.makeText(context, "${checkedTodo.title} 눌림", Toast.LENGTH_SHORT).show()

            val todoId: Int = checkedTodo.id
            update_todo.let {
                it.setText(checkedTodo.title)

                (activity as? FragmentChange)?.change(UpdateFragment())

                val fm: FragmentManager = supportFragmentManager
                val ft: FragmentTransaction = fm.beginTransaction()
                ft.replace(R.id.fragment_main, UpdateFragment())
                ft.commit()
            }

        }

        // checkedTodos가 변하면 data를 adapter.checkedTodoList로 갱신
        viewModel.checkedTodos.observe(viewLifecycleOwner, Observer { data ->
            Log.d("Test", "Data size: ${data?.map { it.id }?.joinToString(",")}")
            if (data != null) {
                adapter.checkedTodoList = arrayListOf<MainViewModel.CheckedTodo>().apply {
                    addAll(data)
                }
            }
        })

        binding.recyclerView.adapter = adapter
        // recyclerView.addItemDecoration(context, 방향) : 데이터 추가될때마다 테두리지정
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        // Button
        binding.addButton.setOnClickListener {
            viewModel.insert()
            Toast.makeText(context, "ADD Complete!!", Toast.LENGTH_SHORT).show()

        }
        binding.delButton.setOnClickListener {
            viewModel.delete()
            Toast.makeText(context, "DEL Complete!!", Toast.LENGTH_SHORT).show()
        }

        viewModel.load()























































    }

}