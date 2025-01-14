package com.mysunnyweather.android

import android.app.Application
import android.content.Context

class MySunnyWeatherApplication :Application() {

    companion object{
        lateinit var context:Context
        const val TOKEN="4sjjF0e97Htp6JLT"
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}