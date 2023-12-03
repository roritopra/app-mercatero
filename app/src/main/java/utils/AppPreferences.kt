package utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {


    private fun prefObj(context: Context):SharedPreferences{
        return context.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
    }


    fun Context.getUserType(): String {
        return prefObj(this).getString("UserType", "").toString()
    }

    fun Context.setUserType(value: String) {
        prefObj(this).edit().putString("UserType", value).apply()
    }

    fun Context.getUserId(): String {
        return prefObj(this).getString("UserId", "").toString()
    }

    fun Context.setUserId(value: String) {
        prefObj(this).edit().putString("UserId", value).apply()
    }





}