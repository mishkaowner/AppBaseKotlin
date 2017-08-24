package com.mishkaowner.appbasekotlinsample

import android.widget.Toast
import com.mishkaowner.appbasekotlin.ui.base.BaseActivity
import com.mishkaowner.appbasekotlin.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView{
    lateinit var presenter : MainPresenterImpl

    override fun displayHelloWorld() {
        Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
    }

    override fun bindView() {
        simple_bt.setOnClickListener { presenter.onSimpleBtClicked() }
    }

    override fun inject() {
        presenter = MainPresenterImpl(this)
    }

    override fun getPresenter(): BasePresenter = presenter

    override fun getLayoutID(): Int = R.layout.activity_main
}
