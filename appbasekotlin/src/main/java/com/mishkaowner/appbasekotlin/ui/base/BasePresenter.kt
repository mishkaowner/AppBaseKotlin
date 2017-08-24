package com.mishkaowner.appbasekotlin.ui.base

interface BasePresenter {
    fun onCreate()
    fun onDestroy()
    fun onSave()
    fun onRestore()
    fun onResume()
    fun onPause()
}