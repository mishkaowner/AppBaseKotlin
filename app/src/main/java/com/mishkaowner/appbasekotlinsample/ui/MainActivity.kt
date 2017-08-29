package com.mishkaowner.appbasekotlinsample.ui

import android.widget.Toast
import com.mishkaowner.appbasekotlin.ui.base.BaseAbstractActivity
import com.mishkaowner.appbasekotlin.ui.base.BasePresenter
import com.mishkaowner.appbasekotlin.util.afterTextChanged
import com.mishkaowner.appbasekotlinsample.di.MainActivityModule
import com.mishkaowner.appbasekotlinsample.MainApp
import com.mishkaowner.appbasekotlinsample.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseAbstractActivity(), MainView {


    @Inject lateinit var presenter: MainPresenter                //Injected elements

    override fun display(it: String) {
        resultText.text = it
    }

    override fun say(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun setListeners() {                                 //set listeners for ui components
        simple_bt.setOnClickListener { presenter.onSimpleBtClicked() }
        nameEdit.afterTextChanged { presenter.onNameChanged(it) }
    }

    override fun inject() {
        MainApp[this].plus(MainActivityModule(this)).inject(this) //Inject dependencies
    }

    override fun getPresenter(): BasePresenter = presenter    //Return injected presenter

    override fun getLayoutID(): Int = R.layout.activity_main  //Return layout_id
}
