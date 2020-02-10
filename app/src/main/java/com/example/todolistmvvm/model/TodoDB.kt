package com.example.todolistmvvm.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDB : RoomDatabase(){
    abstract fun todoDao() : TodoDao
}