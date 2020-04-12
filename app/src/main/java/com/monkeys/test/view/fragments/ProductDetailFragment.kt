package com.monkeys.test.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

import com.monkeys.test.R
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
        initPresenter()
        return mView
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
    }

    override fun showImages(images: Array<String>) {
    }

    override fun showSizes(sizes: Array<Size>) {
    }

    override fun showProductNotAvailableError() {
    }
}
