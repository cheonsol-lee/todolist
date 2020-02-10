package com.example.todolistmvvm.module

import com.example.todolistmvvm.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Context를 많이 사용할 때, 한 곳에 모아서 관리해주는 기능

val mainModules= module {
    viewModel { MainViewModel(androidContext()) }
}

