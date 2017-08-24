package com.mishkaowner.appbasekotlinsample

import android.util.Log
import com.mishkaowner.appbasekotlin.ui.base.BaseAbstractPresenterWithVM
import com.mishkaowner.appbasekotlin.util.applyObservableScheduler
import io.reactivex.rxkotlin.toObservable

class MainPresenterImpl(view: MainView) : BaseAbstractPresenterWithVM<MainView, MainVM>(view), MainPresenter {
    override fun onResumeWithFreshVM(vm: MainVM?) {
        vm?.name = "JHihwan"
        println("Is it null? ${vm == null}")
    }

    override fun onResumeWithRestoredVM(vm: MainVM?) {
        Log.d("Hello World", vm?.name)
    }

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