package com.mishkaowner.appbasekotlin

interface BaseView {
    fun getLayoutID() : Int
    fun inject()
    fun getPresenter() : BasePresenter
}