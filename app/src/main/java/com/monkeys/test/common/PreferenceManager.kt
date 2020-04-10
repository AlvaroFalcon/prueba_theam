package com.monkeys.test.common

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager {
    companion object {
        const val STORE_ID_KEY = "STORE_ID_KEY"
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val NO_STORE_SELECTED_VALUE = -1

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        }

        fun setStoreId(storeId: Int, context: Context) {
            getSharedPreferences(context).edit().putInt(STORE_ID_KEY, storeId).apply()
        }

        fun getStoreId(context: Context): Int {
            return getSharedPreferences(context).getInt(STORE_ID_KEY, NO_STORE_SELECTED_VALUE)
        }
    }
}