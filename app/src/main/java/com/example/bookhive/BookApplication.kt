package com.example.bookhive

import android.app.Application
import com.example.bookhive.data.AppContainer
import com.example.bookhive.data.DefaultAppContainer

class BookApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}