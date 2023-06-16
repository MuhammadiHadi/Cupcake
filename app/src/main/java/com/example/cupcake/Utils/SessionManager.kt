package com.example.cupcake.Utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(private val context: Context) {
    companion object {
        private const val PREF_NAME = "MyPrefs"
        private const val KEY_USER_ID = "currentUserId"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    var currentUserId: Long
        get() = sharedPreferences.getLong(KEY_USER_ID, -1)
        set(value) = editor.putLong(KEY_USER_ID, value).apply()

    fun isLoggedIn(): Boolean {
        return currentUserId != -1L
    }

    fun logout() {
        editor.remove(KEY_USER_ID).apply()

    }
}