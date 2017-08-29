package com.mishkaowner.appbasekotlin.util

import android.content.SharedPreferences
import javax.inject.Inject

class SharedDataEditorImpl @Inject constructor(val sharedPreferences : SharedPreferences) : SharedDataEditor {
    override fun setData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getData(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    override fun removeData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}