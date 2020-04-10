package com.monkeys.test.common

import android.content.Context
import android.content.Intent
import com.monkeys.test.view.activities.MainActivity

class ActivityLauncher {

    companion object {

        fun launchMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

    }
}