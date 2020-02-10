package com.example.todolistmvvm.model

import androidx.room.*

@Dao
interface TodoDao {
    @Query("select * from Todo")
    fun getAll(): List<Todo>

    @Query("select * from Todo where id=:id")
    fun get(id: Int): Todo

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

//    @Delete
//    suspend fun delete(todo: Todo)

    // 입력받은 id에 해당하는 값을 삭제
    @Query("delete from Todo where id=:id")
    suspend fun delete(id: Int)
}