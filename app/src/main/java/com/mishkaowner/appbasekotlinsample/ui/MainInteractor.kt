package com.mishkaowner.appbasekotlinsample.ui

import io.reactivex.Observable

interface MainInteractor {
    fun getUserAge(name : String) : Observable<Int>
}