package com.mishkaowner.appbasekotlinsample.di

import com.mishkaowner.appbasekotlin.di.scope.ActivityScope
import com.mishkaowner.appbasekotlinsample.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(activity : MainActivity)
}