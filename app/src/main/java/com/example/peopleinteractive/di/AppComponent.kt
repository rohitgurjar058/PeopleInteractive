package com.example.peopleinteractive.di

import com.example.peopleinteractive.ui.MatchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, AppModule::class])
interface AppComponent {

    // Classes that can be injected by this Component
    fun inject(matchFragment: MatchFragment)
}