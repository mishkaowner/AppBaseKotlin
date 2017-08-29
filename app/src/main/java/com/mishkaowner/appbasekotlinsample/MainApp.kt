package com.mishkaowner.appbasekotlinsample

import android.content.Context
import com.mishkaowner.appbasekotlin.BaseApp
import com.mishkaowner.appbasekotlin.di.component.DaggerBaseAppComponent
import com.mishkaowner.appbasekotlin.di.module.BaseAppModule
import com.mishkaowner.appbasekotlinsample.di.DaggerMainAppComponent
import com.mishkaowner.appbasekotlinsample.di.MainAppComponent
import com.mishkaowner.appbasekotlinsample.di.MainAppModule
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class MainApp : BaseApp() {
    @Inject lateinit var mainAppComponent: MainAppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        DaggerMainAppComponent.builder()
                .baseAppComponent(DaggerBaseAppComponent.builder().baseAppModule(BaseAppModule(this)).build())
                .mainAppModule(MainAppModule(this))
                .build()
                .inject(this)
    }

    companion object {
        operator fun get(context: Context): MainAppComponent {
            return (context.applicationContext as MainApp).getAppComponent()
        }
    }

    fun getAppComponent(): MainAppComponent {
        return mainAppComponent
    }
}