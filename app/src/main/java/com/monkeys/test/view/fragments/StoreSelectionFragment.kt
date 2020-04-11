package com.monkeys.test.view.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.StoreAdapter
import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.model.Store
import com.monkeys.test.presenter.StoreSelectionPresenter
import com.monkeys.test.view.StoreSelectionView
import kotlinx.android.synthetic.main.fragment_store_selection.view.*

class StoreSelectionFragment : BaseFragment(), StoreSelectionView, NetworkOperationCallback {
    override val networkOperationCallback: NetworkOperationCallback = this
    lateinit var presenter: StoreSelectionPresenter
    private var storeSelectionListener: StoreSelectionView.StoreSelectionListener? = null

    lateinit var mView : View
    private val adapter = StoreAdapter()

    override fun onAttach(context: Context) {
        if(context is StoreSelectionView.StoreSelectionListener){
            this.storeSelectionListener = context
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        navTitle = getString(R.string.store_selection_title)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_store_selection, container, false)
        initRecyclerView()
        initPresenter()
        return mView
    }

    private fun initPresenter() {
        this.presenter = StoreSelectionPresenter(this)
        this.presenter.initView(context)
    }

    private fun initRecyclerView(){
        this.adapter.listener = this.storeSelectionListener
        this.mView.recycler_view.apply {
            layoutManager = LinearLayoutManager(this@StoreSelectionFragment.context)
            adapter = this@StoreSelectionFragment.adapter
        }
    }

    override fun showProgress() {
        mView.progress.visibility = View.VISIBLE
    }

    override fun showStoreList(stores: Array<Store>) {
        hideProgress()
        adapter.refreshData(stores)
    }

    override fun hideProgress() {
        mView.progress.visibility = View.GONE
    }

    override fun showError() {
        hideProgress()
    }
}
