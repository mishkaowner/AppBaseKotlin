package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class MainActivityModule(private val activity : MainActivity) {
    @Provides @ActivityScope fun providesActivity() : MainActivity = activity
    @Provides @ActivityScope fun providesView(activity : MainActivity) : MainView = activity
    @Provides @ActivityScope fun providesMainPresenter(presenter : MainPresenterImpl) : MainPresenter = presenter
    @Provides @ActivityScope fun providesNameSub(): PublishSubject<String> = PublishSubject.create()
    @Provides @ActivityScope fun providesClickSub(): PublishSubject<Boolean> = PublishSubject.create()
}