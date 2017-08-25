package com.mishkaowner.appbasekotlinsample

import android.widget.Toast
import com.mishkaowner.appbasekotlin.ui.base.BaseActivity
import com.mishkaowner.appbasekotlin.ui.base.BasePresenter
import com.mishkaowner.appbasekotlin.util.afterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    @Inject lateinit var presenter: MainPresenter

    override fun sayTheName(name: String) {
        Toast.makeText(this, "Hello World $name", Toast.LENGTH_SHORT).show()
    }

    override fun bindView() {
        simple_bt.setOnClickListener { presenter.onSimpleBtClicked() }
        nameEdit.afterTextChanged { presenter.onNameChanged(it) }
    }

    override fun inject() {
        MainApp[this].plus(MainActivityModule(this)).inject(this)
    }

    override fun getPresenter(): BasePresenter = presenter

    override fun getLayoutID(): Int = R.layout.activity_main
}
