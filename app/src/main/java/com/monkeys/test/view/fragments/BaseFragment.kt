package com.monkeys.test.view.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monkeys.test.R

abstract class BaseFragment : Fragment() {
    var navTitle: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateActivityTitle()
    }

    private fun updateActivityTitle() {
        if (navTitle != null) {
            activity?.apply {
                title = navTitle
            }
        }
    }

    fun showDialog(titleRes: Int, msgRes: Int, iconRes: Int, action: () -> Unit = {}) {
        AlertDialog.Builder(context)
            .setTitle(titleRes)
            .setMessage(msgRes)
            .setIcon(iconRes)
            .setPositiveButton(
                R.string.dialog_positive_btn
            ) { p0, p1 -> action() }
            .show()
    }

}
