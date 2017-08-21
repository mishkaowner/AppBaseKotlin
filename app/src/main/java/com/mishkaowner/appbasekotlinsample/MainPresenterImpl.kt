package com.mishkaowner.appbasekotlinsample

import com.mishkaowner.appbasekotlin.BaseAbstractPresenter

class MainPresenterImpl(view: MainView) : BaseAbstractPresenter<MainView>(view), MainPresenter {
    override fun onSimpleBtClicked() {
        view.displayHelloWorld()
    }

    override fun onResume() {
        super.onResume()
        view.bindView()
    }
}