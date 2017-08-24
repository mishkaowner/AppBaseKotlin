package com.mishkaowner.appbasekotlin.ui.base

interface BaseView {
    fun getLayoutID() : Int
    fun inject()
    fun getPresenter() : BasePresenter?
}