package com.monkeys.test.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.monkeys.test.R

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {
    companion object{
        const val NO_TITLE_VALUE = -1
    }

    var titleRes : Int = NO_TITLE_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(titleRes != NO_TITLE_VALUE){
            activity?.apply {
                title = getString(titleRes)
            }
        }
    }
}
