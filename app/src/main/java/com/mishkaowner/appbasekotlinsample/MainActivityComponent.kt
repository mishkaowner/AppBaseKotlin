package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(activity : MainActivity)
}