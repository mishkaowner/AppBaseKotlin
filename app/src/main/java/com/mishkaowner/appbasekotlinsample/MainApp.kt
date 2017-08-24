package com.mishkaowner.appbasekotlinsample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}