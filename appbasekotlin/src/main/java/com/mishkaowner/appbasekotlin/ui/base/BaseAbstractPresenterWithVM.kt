package com.mishkaowner.appbasekotlin.ui.base

import android.util.Log
import com.mishkaowner.appbasekotlin.util.ISharedDataEditor
import java.lang.reflect.ParameterizedType

abstract class BaseAbstractPresenterWithVM<out V : BaseView, VM : BaseViewModel>(view: V, protected val sharedDataEditor : ISharedDataEditor) : BaseAbstractPresenter<V>(view) {
    protected var vm : VM? = null

    override fun onResume() {
        super.onResume()
        if (vm == null) {
            try {
                val vmClass = (javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[1] as Class<VM>
                vm = vmClass.newInstance()
                if (vm != null) {
                    onResumeWithFreshVM(vm)
                }
            } catch (e: Exception) {
                Log.d("Base", e.toString())
            }

        }
    }

    override fun onSave() {
        super.onSave()
        sharedDataEditor.saveData((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
    }

    override fun onRestore() {
        super.onRestore()
        vm = sharedDataEditor.loadData((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
        if (vm != null) {
            onResumeWithRestoredVM(vm)
        }
    }

    protected abstract fun onResumeWithFreshVM(vm: VM?)

    protected abstract fun onResumeWithRestoredVM(vm: VM?)
}