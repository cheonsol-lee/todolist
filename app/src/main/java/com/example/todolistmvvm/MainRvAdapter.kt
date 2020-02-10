package com.example.todolistmvvm

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistmvvm.databinding.TodoItemBinding
import com.example.todolistmvvm.viewmodel.MainViewModel


/**
 * 1. Adapater의 생성자는 비우자.
 * 2. data는 class내부에서 deligation으로 사용
 * 3. 단, data를 세팅하기 전에 Condition을 지정해주고자 하면 field(Old value), value(new value)로 생각하고 메서드 4개를 재정의하자.
 */

class MainRvAdapter
    : RecyclerView.Adapter<MainRvAdapter.Holder>() {

    var itemClick: ((MainViewModel.CheckedTodo) -> Unit)? = null
    var checkedTodoList: ArrayList<MainViewModel.CheckedTodo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
           /* if (field.isEmpty()) {
                field = value
                notifyDataSetChanged()   // 변화가 있으면 갱신
            } else {
                val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                    // 완전히 동일한 객체인지 비교
                    override fun areItemsTheSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                    ): Boolean {
                        return field[oldItemPosition].id == value[newItemPosition].id
                    }

                    // id상관없이 내용만 동일한지 비교
                    override fun areContentsTheSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                    ): Boolean {
                        return field[oldItemPosition] == value[newItemPosition]
                    }

                    override fun getOldListSize(): Int = field.size
                    override fun getNewListSize(): Int = value.size
                })
                field = value
                result.dispatchUpdatesTo(this)
            }*/
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: TodoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.todo_item,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return checkedTodoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(checkedTodoList[position]) { adapterPosition ->
            itemClick?.invoke(checkedTodoList[adapterPosition])
        }
    }

    class Holder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(checkedTodo: MainViewModel.CheckedTodo, onClick: ((adapterPosition: Int) -> Unit)? = null) {
            binding.checkedTodo = checkedTodo
            itemView.setOnClickListener {
                onClick?.invoke(adapterPosition)
            }
        }
    }
}