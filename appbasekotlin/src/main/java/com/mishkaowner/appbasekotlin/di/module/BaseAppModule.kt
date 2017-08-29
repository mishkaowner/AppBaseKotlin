package com.mishkaowner.appbasekotlin.di.module

import android.content.Context
import com.mishkaowner.appbasekotlin.BaseApp
import com.mishkaowner.appbasekotlin.di.scope.LibraryScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class BaseAppModule(private val app: BaseApp) {
    @Provides @LibraryScope fun providesBaseApp(): BaseApp = app

    @LibraryScope @Provides @Named("AppContext") fun providesAppContext(app: BaseApp): Context = app.applicationContext
}