package com.monkeys.test.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    var navTitle : String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateActivityTitle()
    }

    fun updateActivityTitle() {
        if (navTitle != null) {
            activity?.apply {
                title = navTitle
            }
        }
    }
}
