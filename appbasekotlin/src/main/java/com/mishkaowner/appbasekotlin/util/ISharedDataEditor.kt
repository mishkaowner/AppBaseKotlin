package com.mishkaowner.appbasekotlin.util

interface ISharedDataEditor {
    fun <T> saveData(t: Class<T>)

    fun <T> loadData(t: Class<T>): T
}
