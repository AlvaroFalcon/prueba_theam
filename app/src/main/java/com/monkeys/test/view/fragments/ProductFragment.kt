package com.monkeys.test.view.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.CategoryAdapter
import com.monkeys.test.adapter.CategoryViewType
import com.monkeys.test.model.Category
import com.monkeys.test.model.Product
import com.monkeys.test.presenter.CategoryPresenter
import com.monkeys.test.view.CategoryView
import com.monkeys.test.view.ProductListView
import com.monkeys.test.view.fragments.CategoryFragment.Companion.ARG_CATEGORY
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : BaseFragment(), CategoryView, ProductListView {
    lateinit var categoryPresenter: CategoryPresenter
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var mView: View
    var categorySelectionListener : CategoryView.CategorySelectionListener? = null

    companion object{
        fun newInstance(category: Category?): ProductFragment{
            val productFragment = ProductFragment()
            val args = Bundle()
            category?.let {
                args.putParcelable(ARG_CATEGORY, it)
            }
            productFragment.arguments = args
            return productFragment
        }
    }

    override fun onAttach(context: Context) {
        if(context is CategoryView.CategorySelectionListener){
            this.categorySelectionListener = context
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_product, container, false)
        initCategories()
        navTitle = categoryPresenter.category?.name
        return mView
    }

    private fun initCategories() {
        initCategoriesAdapter()
        initCategoriesPresenter()
    }

    private fun initCategoriesAdapter() {
        this.categoryAdapter = CategoryAdapter(CategoryViewType.HORIZONTAL)
        this.categoryAdapter.listener = this.categorySelectionListener
        this.mView.categories_recycler_view.apply {
            layoutManager = LinearLayoutManager(
                this@ProductFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = categoryAdapter
        }
    }

    private fun initCategoriesPresenter() {
        this.categoryPresenter = CategoryPresenter(this)
        this.categoryPresenter.handleArgs(arguments, context)
    }

    override fun showCategories(categories: Array<Category>) {
        categoryAdapter.refreshData(categories)
    }

    override fun showProducts(products: Array<Product>) {

    }


}
