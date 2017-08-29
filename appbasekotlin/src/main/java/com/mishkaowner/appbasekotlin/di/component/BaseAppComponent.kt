package com.mishkaowner.appbasekotlin.di.component

import android.content.Context
import com.mishkaowner.appbasekotlin.di.module.BaseAppModule
import com.mishkaowner.appbasekotlin.di.module.SharedDataEditorModule
import com.mishkaowner.appbasekotlin.di.scope.LibraryScope
import com.mishkaowner.appbasekotlin.util.SharedDataEditor
import dagger.Component
import javax.inject.Named

@LibraryScope
@Component(modules = arrayOf(BaseAppModule::class, SharedDataEditorModule::class))
interface BaseAppComponent {
    @Named("AppContext") fun providesAppContext() : Context

    fun providesSharedDataEditor() : SharedDataEditor
}