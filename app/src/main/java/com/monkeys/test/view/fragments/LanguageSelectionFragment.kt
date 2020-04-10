package com.monkeys.test.view.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.monkeys.test.R
import com.monkeys.test.adapter.LanguageAdapter
import com.monkeys.test.model.StoreView
import com.monkeys.test.presenter.LanguageSelectionPresenter
import com.monkeys.test.view.LanguageSelectionView
import kotlinx.android.synthetic.main.fragment_language_selection.view.*


class LanguageSelectionFragment : Fragment(), LanguageSelectionView{

    lateinit var mView: View
    private var languageSelectionListener: LanguageSelectionView.LanguageSelectionListener? = null
    private val adapter = LanguageAdapter()
    lateinit var presenter: LanguageSelectionPresenter

    companion object {
        const val ARG_STORE_VIEW = "ARG_STORE_VIEW"
        fun newInstance(storeView: Array<StoreView>): LanguageSelectionFragment{
            val fragment = LanguageSelectionFragment()
            val args = Bundle()
            args.putParcelableArray(ARG_STORE_VIEW, storeView)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        if(context is LanguageSelectionView.LanguageSelectionListener){
            this.languageSelectionListener = context
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_language_selection, container, false)
        initRecyclerView()
        presenter = LanguageSelectionPresenter(this)
        handleArguments()
        return this.mView
    }

    private fun handleArguments() {
        arguments?.apply {
            if(containsKey(ARG_STORE_VIEW)){
                presenter.storeViews = getParcelableArray(ARG_STORE_VIEW) as Array<StoreView>
                presenter.showList()
            }
        }
    }

    private fun initRecyclerView(){
        this.adapter.listener = this.languageSelectionListener
        this.mView.recycler_view.apply {
            layoutManager = LinearLayoutManager(this@LanguageSelectionFragment.context)
            adapter = this@LanguageSelectionFragment.adapter
        }
    }

    override fun showLanguages(storeViews: Array<StoreView>) {
        this.adapter.refreshData(storeViews)
    }


}
