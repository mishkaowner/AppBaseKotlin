package com.mishkaowner.appbasekotlinsample.ui

import com.mishkaowner.appbasekotlin.ui.base.BasePresenter

interface MainPresenter : BasePresenter {
    fun onSimpleBtClicked()
    fun onNameChanged(it: String)
}