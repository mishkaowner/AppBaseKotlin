package com.mishkaowner.appbasekotlinsample.ui

import android.util.Log
import com.mishkaowner.appbasekotlin.ui.base.BaseAbstractPresenterWithVM
import com.mishkaowner.appbasekotlin.util.applyObservableScheduler
import io.reactivex.Observable
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(view: MainView,
                                            var mainInteractor: MainInteractor) : BaseAbstractPresenterWithVM<MainView, MainVM>(view), MainPresenter {
    val logId = "LOG_MainPresenterImpl"

    override fun onNameChanged(it: String) {
        vm?.name = it
    }

    override fun onSimpleBtClicked() {
        disposeOnPause?.add(Observable.just(vm?.name ?: "")
                .filter { it.isNotEmpty() }
                .flatMap { name -> mainInteractor.getUserAge(name).doOnNext { vm?.age = it } }
                .map { "Hi, ${vm?.name}! You are ${vm?.age} yrs old." }
                .applyObservableScheduler()
                .subscribe({
                    view.say(it)
                    view.display(it)
                }, { Log.e(logId, it.message) }))
    }

    override fun onResumeWithFreshVM(vm: MainVM?) {
        Log.d(logId, "name from fresh vm ${vm?.name}")
        view.display("Type in your name and find out how old you are!")
    }

    override fun onResumeWithRestoredVM(vm: MainVM?) {
        Log.d(logId, "name from restored vm ${vm?.name}")
        view.display("Welcome back! ${vm?.name}!\nYou were ${vm?.age} yrs old.")
    }

    override fun onResume() {
        super.onResume()
        view.setListeners()
    }
}