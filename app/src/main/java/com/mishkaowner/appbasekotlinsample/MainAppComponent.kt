package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.di.component.BaseAppComponent
import com.mishkaowner.appbasekotlin.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(dependencies = arrayOf(BaseAppComponent::class), modules = arrayOf(MainAppModule::class))
interface MainAppComponent {
    fun inject(app : MainApp)
    fun plus(module : MainActivityModule) : MainActivityComponent
}