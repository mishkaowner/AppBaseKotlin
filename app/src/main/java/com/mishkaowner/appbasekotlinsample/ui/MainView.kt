package com.mishkaowner.appbasekotlinsample.ui

import com.mishkaowner.appbasekotlin.ui.base.BaseView

interface MainView : BaseView {
    fun setListeners()
    fun say(it: String)
    fun display(it: String)
}