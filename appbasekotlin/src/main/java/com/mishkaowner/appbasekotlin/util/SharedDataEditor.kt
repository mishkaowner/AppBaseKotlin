package com.mishkaowner.appbasekotlin.util

interface SharedDataEditor {
    fun setData(key: String, value: String)

    fun getData(key: String): String

    fun removeData(key: String)
}