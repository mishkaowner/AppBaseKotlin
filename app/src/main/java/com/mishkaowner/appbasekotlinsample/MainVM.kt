package com.mishkaowner.appbasekotlinsample

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey



open class MainVM : RealmObject() {
    @PrimaryKey var id: Long = 0

    @Index var title: String? = null

    var name: String? = null
}