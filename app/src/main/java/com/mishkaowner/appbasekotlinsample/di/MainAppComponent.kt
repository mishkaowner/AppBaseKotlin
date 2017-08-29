package com.mishkaowner.appbasekotlinsample.di

import com.mishkaowner.appbasekotlin.di.component.BaseAppComponent
import com.mishkaowner.appbasekotlin.di.scope.ApplicationScope
import com.mishkaowner.appbasekotlinsample.MainApp
import dagger.Component

@ApplicationScope
@Component(dependencies = arrayOf(BaseAppComponent::class), modules = arrayOf(MainAppModule::class))
interface MainAppComponent {
    fun inject(app : MainApp)
    fun plus(module : MainActivityModule) : MainActivityComponent
}