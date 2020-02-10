package com.example.todolistmvvm.module

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


// 앱이 생성될 때 제일 먼저 실행되는 클래스
// manifest의 name에 지정해야함

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // koin의 기능으로 modules()함수로 mainModules에 있는 기능을 우선적으로 실행하겠다.
        startKoin {
            androidContext(this@App)
            modules(mainModules)
        }
    }
}