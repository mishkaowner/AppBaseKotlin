package com.mishkaowner.appbasekotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : AppCompatActivity(), BaseView {
    protected var disposeOnPause : CompositeDisposable? = null
    protected var disposeOnDestroy : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposeOnDestroy = CompositeDisposable()
        setContentView(getLayoutID())
        inject()
        if (savedInstanceState != null) {
            getPresenter()?.onRestore()
        } else {
            getPresenter()?.onCreate()
        }
    }


    public override fun onResume() {
        super.onResume()
        disposeOnPause = CompositeDisposable()
        getPresenter()?.onResume()
    }

    public override fun onPause() {
        super.onPause()
        getPresenter()?.onPause()
        if (disposeOnPause != null && !(disposeOnPause?.isDisposed ?:true)) {
            disposeOnPause?.dispose()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter()?.onSave()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
        if (disposeOnDestroy != null && !(disposeOnDestroy?.isDisposed ?:true)) {
            disposeOnDestroy?.dispose()
        }
    }
}