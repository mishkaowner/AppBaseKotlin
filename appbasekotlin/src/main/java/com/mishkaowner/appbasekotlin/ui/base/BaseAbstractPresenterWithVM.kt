package com.mishkaowner.appbasekotlin.ui.base

import android.util.Log
import io.realm.Realm
import io.realm.RealmObject
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseAbstractPresenterWithVM<out V : BaseView, VM : RealmObject>(view: V) : BaseAbstractPresenter<V>(view) {
    protected var vm: VM? = null
    private var realm: Realm? = null

    override fun onCreate() {
        super.onCreate()
        realm = Realm.getDefaultInstance()
    }

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
        realm?.executeTransaction { it.insertOrUpdate(vm) }
    }

    override fun onRestore() {
        super.onRestore()
        realm = Realm.getDefaultInstance()
        realm?.executeTransaction {
            vm = it.where((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>).findFirst()
            if (vm != null) {
                onResumeWithRestoredVM(vm)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
    }

    protected abstract fun onResumeWithFreshVM(vm: VM?)

    protected abstract fun onResumeWithRestoredVM(vm: VM?)
}