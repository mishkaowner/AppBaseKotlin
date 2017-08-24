package com.mishkaowner.appbasekotlin.ui.base

import io.reactivex.disposables.CompositeDisposable


abstract class BaseAbstractPresenter<out V : BaseView>(protected val view: V) : BasePresenter {
    protected var disposeOnPause: CompositeDisposable? = null
    protected var disposeOnDestroy: CompositeDisposable? = null

    override fun onResume() {
        disposeOnPause = CompositeDisposable()
    }

    override fun onPause() {
        if (!(disposeOnPause?.isDisposed ?:true)) {
            disposeOnPause?.dispose()
        }
    }

    override fun onSave() {}

    override fun onRestore() {}

    override fun onCreate() {
        disposeOnDestroy = CompositeDisposable()
    }

    override fun onDestroy() {
        if (!(disposeOnDestroy?.isDisposed ?:true)) {
            disposeOnDestroy?.dispose()
        }
    }
}