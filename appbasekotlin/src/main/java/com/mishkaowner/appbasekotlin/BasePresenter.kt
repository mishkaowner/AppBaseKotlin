package com.mishkaowner.appbasekotlin

interface BasePresenter {
    fun onCreate()
    fun onDestroy()
    fun onSave()
    fun onRestore()
    fun onResume()
    fun onPause()
}