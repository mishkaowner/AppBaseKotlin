package com.mishkaowner.appbasekotlin

import io.reactivex.disposables.CompositeDisposable


abstract class BaseAbstractPresenter<V : BaseView> : BasePresenter {
    protected var disposeOnPause: CompositeDisposable? = null
    protected var disposeOnDestroy:CompositeDisposable? = null
    protected var view : V

    constructor(view : V) {
        this.view = view
    }

    override fun onResume() {
        disposeOnPause = CompositeDisposable()
    }

    override fun onPause() {
        if (disposeOnPause != null && !disposeOnPause!!.isDisposed) {
            disposeOnPause!!.dispose()
        }
    }

    override fun onSave() {}

    override fun onRestore() {}

    override fun onCreate() {
        disposeOnDestroy = CompositeDisposable()
    }

    override fun onDestroy() {
        if (disposeOnDestroy != null && !disposeOnDestroy!!.isDisposed) {
            disposeOnDestroy!!.dispose()
        }
    }
}