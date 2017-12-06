package com.amigotrip.android

import android.content.Context
import android.content.SharedPreferences
import com.amigotrip.android.datas.User
import com.amigotrip.anroid.R

/**
 * Created by Zimincom on 2017. 11. 16..
 */
//rename
object UserInfoManager {

    private lateinit var preference: SharedPreferences
    private lateinit var user: User
    private lateinit var firebaseUserKey: String

    fun initManager(context: Context) {
        preference = context.getSharedPreferences(context.getString(R.string.KEY_PREFERENCE),
                Context.MODE_PRIVATE)
    }

    fun setUserInfo(user: User?) {
        val editor = preference.edit()
        editor.putInt(AppKeys.userId, user!!.id!!)
        editor.putString(AppKeys.userName, user.name)
        editor.putString(AppKeys.userEmail, user.email)
        editor.putBoolean(AppKeys.isLogin, true)
        editor.apply()

        this.user = user!!
    }

    fun setPassword(password: String) {
        val editor = preference.edit()
        editor.putString(AppKeys.password, password)
        editor.apply()
    }


    fun setKey(key: String) {
        firebaseUserKey = key
        val editor = preference.edit()
        editor.putString(AppKeys.userFirebaseKey, key)
        editor.apply()
    }

    fun getUserFirebaseKey() : String{
        return preference.getString(AppKeys.userFirebaseKey, "")
    }

    fun getPreference() = preference

    fun isUserLogin() : Boolean {
        return preference.getBoolean(AppKeys.isLogin, false)
    }

    fun removeUser() {
        preference.edit().clear().apply()
    }

    fun getLogineduser(): User{
        val name = preference.getString(AppKeys.userName, "no name")
        val email = preference.getString(AppKeys.userEmail, "no email")
        val password = preference.getString(AppKeys.password, " ")
        val id = preference.getInt(AppKeys.userId, 0)

        return User(name = name, email = email, id = id, profileImg = null, password = password)
    }


}