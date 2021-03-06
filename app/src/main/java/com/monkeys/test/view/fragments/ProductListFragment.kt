package com.monkeys.test.view.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.CategoryAdapter
import com.monkeys.test.adapter.CategoryViewType
import com.monkeys.test.adapter.ProductAdapter
import com.monkeys.test.common.ActivityLauncher
import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.common.PreferenceManager
import com.monkeys.test.model.Category
import com.monkeys.test.model.Product
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.ProductFilter
import com.monkeys.test.presenter.CategoryPresenter
import com.monkeys.test.presenter.ProductPresenter
import com.monkeys.test.view.CategoryView
import com.monkeys.test.view.ProductListView
import com.monkeys.test.view.activities.FilterActivity
import com.monkeys.test.view.fragments.CategoryFragment.Companion.ARG_CATEGORY
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductListFragment : BaseFragment(), CategoryView, ProductListView, NetworkOperationCallback {
    override val networkOperationCallback: NetworkOperationCallback = this

    private var categoryPresenter: CategoryPresenter? = null
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var productAdapter: ProductAdapter
    private var productFilter: ProductFilter? = null
    private var productPresenter: ProductPresenter? = null
    private lateinit var mView: View
    private var categorySelectionListener: CategoryView.CategorySelectionListener? = null
    private var productSelectionListener: ProductListView.ProductSelectionListener? = null
    companion object {

        fun newInstance(category: Category?): ProductListFragment {
            val productFragment = ProductListFragment()
            val args = Bundle()
            category?.let {
                args.putParcelable(ARG_CATEGORY, it)
            }
            productFragment.arguments = args
            return productFragment
        }
    }
    override fun onAttach(context: Context) {
        if (context is CategoryView.CategorySelectionListener) {
            this.categorySelectionListener = context
        }
        if (context is ProductListView.ProductSelectionListener) {
            this.productSelectionListener = context
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_product, container, false)
        if(savedInstanceState != null) restoreScreenState(savedInstanceState)
        initCategories()
        initProducts()
        navTitle = categoryPresenter?.category?.name
        return mView
    }

    private fun restoreScreenState(savedInstanceState: Bundle) {
        this.productFilter = savedInstanceState.getSerializable(FilterActivity.ARG_FILTER) as ProductFilter?
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_filter_list).apply { isVisible = true }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter_list ->{
                activity?.let {
                    ActivityLauncher.launchFilterActivity(it, productFilter)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initProducts() {
        initProductAdapter()
        initProductPresenter()
    }

    private fun initCategories() {
        initCategoriesAdapter()
        initCategoriesPresenter()
    }

    private fun initProductPresenter() {
        if (productPresenter == null) {
            this.productPresenter = ProductPresenter(this)
        }

        val category = categoryPresenter?.category
        if(productFilter == null){
            context?.let {
                productFilter = ProductFilter(storeId = PreferenceManager.getStoreId(it), categoryId = category?.categoryId ?: 0)
            }
        }
        retrieveProducts()
    }

    private fun retrieveProducts() {
        productFilter?.let {
            productPresenter?.initView(it)
        }
    }

    private fun initProductAdapter() {
        context?.let {
            productAdapter = ProductAdapter(it, this.productSelectionListener)
            mView.product_grid_view.apply {
                adapter = productAdapter
            }
        }
    }

    private fun initCategoriesAdapter() {
        this.categoryAdapter = CategoryAdapter(CategoryViewType.HORIZONTAL)
        this.categoryAdapter.listener = this.categorySelectionListener
        this.mView.categories_recycler_view.apply {
            layoutManager = LinearLayoutManager(
                this@ProductListFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = categoryAdapter
        }
    }

    private fun initCategoriesPresenter() {
        if (this.categoryPresenter == null) {
            this.categoryPresenter = CategoryPresenter(this)
        }
        this.categoryPresenter?.handleArgs(arguments, context)
    }

    override fun showCategories(categories: Array<Category>) {
        categoryAdapter.refreshData(categories)
    }

    override fun showProducts(products: Array<Product>) {
        hideProgress()
        productAdapter.refreshData(products)
    }

    override fun showProgress() {
        mView.progress.visibility = View.VISIBLE
    }

    override fun showError() {
        hideProgress()
        showDialog(R.string.dialog_error_title, R.string.dialog_error_body, R.drawable.ic_error_24dp)
    }

    override fun hideProgress() {
        mView.progress.visibility = View.GONE
    }

    override fun applyFilter(filter: ProductFilter) {
        this.productFilter = filter
        this.productPresenter?.retrieveProducts(filter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(FilterActivity.ARG_FILTER, this.productFilter)
    }

    override fun setAvailableFilters(filters: Array<Filter>) {
        this.productFilter?.availableFilters = filters
    }

}
