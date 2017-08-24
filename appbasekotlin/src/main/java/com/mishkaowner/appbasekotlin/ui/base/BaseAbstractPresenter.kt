package com.mishkaowner.appbasekotlin.ui.base

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


abstract class BaseAbstractPresenter<out V : BaseView>(protected val view: V) : BasePresenter {
    protected var disposeOnPause: CompositeDisposable? = null
    protected var disposeOnDestroy: CompositeDisposable? = null

    override fun onResume() {
        disposeOnPause = CompositeDisposable()
    }

    override fun onPause() {
        if (disposeOnPause != null && !(disposeOnPause?.isDisposed ?:true)) {
            disposeOnPause?.dispose()
        }
    }

    override fun onSave() {}

    override fun onRestore() {}

    override fun onCreate() {
        disposeOnDestroy = CompositeDisposable()
    }

    override fun onDestroy() {
        if (disposeOnDestroy != null && !(disposeOnDestroy?.isDisposed ?:true)) {
            disposeOnDestroy?.dispose()
        }
    }

    protected fun <T> Observable<T>.applyObservableScheduler() = subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())!!
    protected fun Completable.applyCompletableSchedulers() = subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())!!
    protected fun <T> Maybe<T>.applyMaybeSchedulers() = subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())!!
}