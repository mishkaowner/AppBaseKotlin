package com.mishkaowner.appbasekotlin.ui.base

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAbstractDialogFragment : DialogFragment(), BaseView {
    protected val NO_LAYOUT: Int = -1
    protected var disposeOnPause: CompositeDisposable? = null
    protected var disposeOnDestroy: CompositeDisposable? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        try {
            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        } catch (e: Exception) {
            Log.d("Base", e.toString())
        }
        return dialog
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        if (!enter && parentFragment != null && parentFragment.isRemoving) {
            val doNothingAnim = AlphaAnimation(1f, 1f)
            doNothingAnim.duration = getNextAnimationDuration(parentFragment, 250)
            return doNothingAnim
        } else {
            try {
                var anim: Animation = super.onCreateAnimation(transit, enter, nextAnim)
                if (nextAnim != 0) {
                    anim = AnimationUtils.loadAnimation(context, nextAnim)
                }
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationRepeat(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {}
                })
                return anim
            } catch (e: Exception) {
                return super.onCreateAnimation(transit, enter, nextAnim)
            }
        }
    }

    private fun getNextAnimationDuration(fragment: Fragment, defValue: Long): Long {
        try {
            val nextAnimField = Fragment::class.java.getDeclaredField("mNextAnim")
            nextAnimField.isAccessible = true
            val nextAnimResource = nextAnimField.getInt(fragment)
            val nextAnim = AnimationUtils.loadAnimation(fragment.activity, nextAnimResource)
            return nextAnim?.duration ?: defValue
        } catch (ex: NoSuchFieldException) {
            Log.d("Base", ex.toString())
            return defValue
        } catch (ex: IllegalAccessException) {
            Log.d("Base", ex.toString())
            return defValue
        } catch (ex: Resources.NotFoundException) {
            Log.d("Base", ex.toString())
            return defValue
        }
    }

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