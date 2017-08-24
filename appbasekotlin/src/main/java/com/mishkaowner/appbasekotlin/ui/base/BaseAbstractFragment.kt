package com.mishkaowner.appbasekotlin.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAbstractFragment : Fragment(), BaseView {
    protected val NO_LAYOUT: Int = -1
    protected var disposeOnPause: CompositeDisposable? = null
    protected var disposeOnDestroy: CompositeDisposable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            getPresenter()?.onRestore()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutID() != NO_LAYOUT && container != null) {
            return inflater?.inflate(getLayoutID(), container, false)
        } else {
            return null
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposeOnDestroy = CompositeDisposable()
        inject()
        getPresenter()?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        disposeOnPause = CompositeDisposable()
        getPresenter()?.onResume()
    }

    override fun onPause() {
        super.onPause()
        getPresenter()?.onPause()
        if (!(disposeOnPause?.isDisposed ?: true)) {
            disposeOnPause?.dispose()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter()?.onDestroy()
        if (!(disposeOnDestroy?.isDisposed ?: true)) {
            disposeOnDestroy?.dispose()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        getPresenter()?.onSave()
    }
}