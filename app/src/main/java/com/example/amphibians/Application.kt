package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.DefaultAppContainer

class Application: Application(){

    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}