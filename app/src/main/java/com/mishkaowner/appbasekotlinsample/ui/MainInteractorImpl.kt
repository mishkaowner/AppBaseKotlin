package com.mishkaowner.appbasekotlinsample.ui

import io.reactivex.Observable
import javax.inject.Inject

class MainInteractorImpl @Inject constructor() : MainInteractor {
    override fun getUserAge(name: String): Observable<Int> {
        return Observable.just(name).map { name.length }
    }
}