package com.mishkaowner.appbasekotlin.ui.base

import android.util.Log
import com.google.gson.Gson
import com.mishkaowner.appbasekotlin.util.SharedDataEditor
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseAbstractPresenterWithVM<out V : BaseView, VM : BaseViewModel>(view: V) : BaseAbstractPresenter<V>(view) {
    protected var vm: VM? = null
    @Inject lateinit var sharedDataEditor: SharedDataEditor

    override fun onResume() {
        super.onResume()
        if (vm == null) {
            try {
                val vmClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
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
        val vmClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        saveData(vmClass)
    }

    private fun <T> saveData(t: Class<T>) {
        sharedDataEditor.setData(t.name, Gson().toJson(vm))
    }

    private fun <T> loadData(t: Class<T>): T {
        val data = sharedDataEditor.getData(t.name)
        return Gson().fromJson(data, t)
    }

    override fun onRestore() {
        super.onRestore()
        val vmClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        vm = loadData(vmClass)
        if (vm != null) {
            onResumeWithRestoredVM(vm)
        }
    }

    protected abstract fun onResumeWithFreshVM(vm: VM?)

    protected abstract fun onResumeWithRestoredVM(vm: VM?)
}