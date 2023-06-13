package com.example.storyapp.data

import android.content.Context

class UserPreference(context: Context) {
    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        val edit = preference.edit()
        edit.putString(TOKEN, token)
        edit.apply()
    }

    fun setLoginStatus(isLogin: Boolean) {
        val edit = preference.edit()
        edit.putBoolean(LOGIN, isLogin)
        edit.apply()
    }

    fun getLoginStatus(): Boolean {
        return preference.getBoolean(LOGIN, false)
    }

    fun getToken(): String? {
        return preference.getString(TOKEN, null)
    }

    fun clearToken() {
        val edit = preference.edit()
        edit.clear()
        edit.remove(TOKEN)
        edit.apply()
    }

    companion object {
        const val PREF_NAME = "login_pref"
        const val TOKEN = "token"
        const val LOGIN = "login"
    }
//    val getToken = preference.getString(TOKEN, "")
//    val isLogin = preference.getBoolean(LOGIN, false)
}