package com.mishkaowner.appbasekotlin.util

class SharedDataEditor : ISharedDataEditor {
    override fun <T> loadData(t: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> saveData(t: Class<T>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /*override fun <T> loadData(t: Class<T>): T {
        sharedDataEditor.setData(t, vm)
    }

    override fun <T> saveData(t: Class<T>) {
        return sharedDataEditor.getData(t)
    }*/
}