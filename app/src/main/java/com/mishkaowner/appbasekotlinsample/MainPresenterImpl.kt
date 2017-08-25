package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.ui.base.BaseAbstractPresenterWithVM
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(view: MainView) : BaseAbstractPresenterWithVM<MainView, MainVM>(view), MainPresenter {
    @Inject lateinit var nameSub: PublishSubject<String>
    @Inject lateinit var clickSub: PublishSubject<Boolean>

    override fun onNameChanged(it: String) {
        vm?.name = it
        nameSub.onNext(it)
    }

    override fun onSimpleBtClicked() {
        println("Value ${vm?.name}")
        clickSub.onNext(true)
    }

    override fun onResumeWithFreshVM(vm: MainVM?) {
        println("fresh ${vm?.name}")
    }

    override fun onResumeWithRestoredVM(vm: MainVM?) {
        println("Restored ${vm?.name}")
    }

    override fun onResume() {
        super.onResume()
        view.bindView()
    }
}