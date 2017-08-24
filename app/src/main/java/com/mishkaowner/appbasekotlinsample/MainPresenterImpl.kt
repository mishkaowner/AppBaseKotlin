package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.ui.base.BaseAbstractPresenter
import com.mishkaowner.appbasekotlin.util.applyObservableScheduler
import io.reactivex.rxkotlin.toObservable

class MainPresenterImpl(view: MainView) : BaseAbstractPresenter<MainView>(view), MainPresenter {
    override fun onSimpleBtClicked() {
        val s = listOf("asfd", "asdf")
        s.toObservable().applyObservableScheduler().subscribe({println("it is $it")})
        view.displayHelloWorld()
    }

    override fun onResume() {
        super.onResume()
        view.bindView()
    }
}