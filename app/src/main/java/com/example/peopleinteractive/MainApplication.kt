package com.example.peopleinteractive

import android.app.Application
import com.example.peopleinteractive.di.AppComponent
import com.example.peopleinteractive.di.AppModule
import com.example.peopleinteractive.di.DaggerAppComponent

// appComponent lives in the Application class to share its lifecycle
open class MainApplication : Application() {

    // Reference to the application graph that is used across the whole app
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
    }
}