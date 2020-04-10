package com.monkeys.test.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.CategoryAdapter
import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.model.Category
import com.monkeys.test.presenter.CategoryPresenter
import com.monkeys.test.view.CategoryView
import kotlinx.android.synthetic.main.fragment_category.view.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : BaseFragment(), CategoryView, NetworkOperationCallback {
    lateinit var mView: View
    lateinit var presenter: CategoryPresenter
    lateinit var adapter: CategoryAdapter

    companion object {
        const val ARG_CATEGORY = "ARG_CATEGORY"

        fun newInstance(): CategoryFragment{
            return CategoryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        titleRes = R.string.categories_title
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_category, container, false)
        initRecyclerView()
        initPresenter()
        return mView
    }

    private fun initPresenter() {
        this.presenter = CategoryPresenter(this, this)
        this.presenter.handleArgs(arguments, context)
    }

    private fun initRecyclerView() {
        this.adapter = CategoryAdapter()
        this.mView.recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CategoryFragment.context)
            adapter = this@CategoryFragment.adapter
        }
    }

    override fun showCategories(categories: Array<Category>) {
        hideProgress()
        this.adapter.refreshData(categories)
    }

    override fun showProgress() {
        this.mView.progress.visibility = View.VISIBLE
    }

    override fun showError() {
    }

    override fun hideProgress() {
        this.mView.progress.visibility = View.GONE
    }
}
