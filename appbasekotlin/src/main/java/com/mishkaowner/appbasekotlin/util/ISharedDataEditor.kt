package com.mishkaowner.appbasekotlin.util

interface ISharedDataEditor {
    fun <T> saveData(t: Class<T>) /*{
        sharedDataEditor.setData(t, vm)
    }*/
    fun <T> loadData(t: Class<T>): T /*{
        return sharedDataEditor.getData(t)
    }*/
}
