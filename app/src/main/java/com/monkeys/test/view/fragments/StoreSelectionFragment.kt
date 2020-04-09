package com.monkeys.test.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.StoreAdapter
import com.monkeys.test.model.Store
import com.monkeys.test.presenter.StoreSelectionPresenter
import com.monkeys.test.view.StoreSelectionView
import kotlinx.android.synthetic.main.fragment_store_selection.view.*

class StoreSelectionFragment : Fragment(), StoreSelectionView, StoreSelectionView.StoreSelectionListener {
    lateinit var presenter: StoreSelectionPresenter

    lateinit var mView : View
    private val adapter = StoreAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_store_selection, container, false)
        this.mView.recycler_view.apply {
            layoutManager = LinearLayoutManager(this@StoreSelectionFragment.context)
            adapter = this@StoreSelectionFragment.adapter
        }
        this.presenter = StoreSelectionPresenter(this)
        this.presenter.retrieveStores()
        return mView
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

    override fun onStoreSelected(store: Store) {

    }
}
