package com.mishkaowner.appbasekotlin.di.module

import android.content.Context
import android.content.SharedPreferences
import com.mishkaowner.appbasekotlin.di.scope.LibraryScope
import com.mishkaowner.appbasekotlin.util.SharedDataEditor
import com.mishkaowner.appbasekotlin.util.SharedDataEditorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedDataEditorModule {
    @LibraryScope @Provides fun providesSharedPreferences(@Named("AppContext") context: Context): SharedPreferences = context.getSharedPreferences("DEFAULT", Context.MODE_PRIVATE)

    @LibraryScope @Provides fun providesSharedDataEditor(sharedDataEditor: SharedDataEditorImpl): SharedDataEditor = sharedDataEditor
}