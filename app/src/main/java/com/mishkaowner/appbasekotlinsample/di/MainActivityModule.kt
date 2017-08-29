package com.mishkaowner.appbasekotlinsample.di

import com.mishkaowner.appbasekotlin.di.scope.ActivityScope
import com.mishkaowner.appbasekotlinsample.ui.*
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity : MainActivity) {
    @Provides @ActivityScope fun providesActivity() : MainActivity = activity
    @Provides @ActivityScope fun providesView(activity : MainActivity) : MainView = activity
    @Provides @ActivityScope fun providesMainPresenter(presenter : MainPresenterImpl) : MainPresenter = presenter
    @Provides @ActivityScope fun providesMainInteractor(interactor: MainInteractorImpl) : MainInteractor = interactor
}