package com.monkeys.test.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.monkeys.test.R
import com.monkeys.test.adapter.ImageAdapter
import com.monkeys.test.adapter.SizesAdapter
import com.monkeys.test.model.Product
import com.monkeys.test.model.Size
import com.monkeys.test.presenter.ProductDetailPresenter
import com.monkeys.test.view.ProductDetailView
import com.monkeys.test.view.activities.ProductDetailActivity
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import kotlinx.android.synthetic.main.product_detail_content.view.*

class ProductDetailFragment : BaseFragment(), ProductDetailView {
    lateinit var presenter: ProductDetailPresenter
    lateinit var mView: View
    lateinit var sizeAdapter: SizesAdapter
    lateinit var imageAdapter: ImageAdapter

    companion object {

        fun newInstance(product: Product?): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ProductDetailActivity.EXTRA_PRODUCT, product)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        initSizeAdapter()
        initImageAdapter()
        initPresenter()
        return mView
    }

    private fun initImageAdapter() {
        this.imageAdapter = ImageAdapter()
        this.mView.image_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ProductDetailFragment.imageAdapter
        }
    }

    private fun initSizeAdapter() {
        this.sizeAdapter = SizesAdapter()
        this.mView.sizes_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ProductDetailFragment.sizeAdapter
        }
    }

    private fun initPresenter() {
        this.presenter = ProductDetailPresenter(this)
        this.presenter.handleArgs(arguments)
    }

    override fun showProductInformation(product: Product) {
        mView.toolbar.title = product.name
        mView.title_text.text = product.name
        mView.price_text.text = getString(R.string.product_price_eur, product.finalPrice)
        mView.description_content.text = product.description
        mView.color_value.text = product.color
        mView.composition_value.text = product.composition
        showSizes(product.sizes)
        showImages(product.images)
    }

    override fun showImages(images: Array<String>) {
        imageAdapter.refreshData(images)
    }

    override fun showSizes(sizes: Array<Size>) {
        sizeAdapter.refreshData(sizes)
    }

    override fun showProductNotAvailableError() {
    }
}
