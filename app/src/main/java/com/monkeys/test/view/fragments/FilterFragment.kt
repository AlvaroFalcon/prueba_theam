package com.monkeys.test.view.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import com.appyvet.materialrangebar.RangeBar

import com.monkeys.test.R
import com.monkeys.test.model.filter.FilterOrder
import com.monkeys.test.model.filter.ProductFilter
import com.monkeys.test.view.activities.FilterActivity.Companion.ARG_FILTER
import kotlinx.android.synthetic.main.fragment_filter.view.*

class FilterFragment : Fragment(), RangeBar.OnRangeBarChangeListener,
    AdapterView.OnItemSelectedListener {
    lateinit var mView: View
    lateinit var filter: ProductFilter

    companion object {
        fun newInstance(productFilter: ProductFilter?): FilterFragment {
            val fragment = FilterFragment()
            val args = bundleOf(ARG_FILTER to productFilter)
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_filter, container, false)
        mView.order_by_spinner.onItemSelectedListener = this
        handleArgs()
        restoreFilterSettings()
        mView.price_range.setOnRangeBarChangeListener(this)
        context?.let {
            val adapter = ArrayAdapter<FilterOrder>(
                it,
                android.R.layout.simple_spinner_item,
                FilterOrder.getFilterOrderList(it)
            )
            mView.order_by_spinner.adapter = adapter
        }
        mView.search_btn.setOnClickListener {
            saveFilterChanges()
            activity?.finish()
        }
        return mView
    }

    private fun restoreFilterSettings() {
        restorePriceSettings()
        restoreTextSetting()
        restoreOrderSetting()
    }

    private fun restoreOrderSetting() {
    }

    private fun restoreTextSetting() {
    }

    private fun restorePriceSettings() {
        filter.priceMin?.let { setPrice(mView.min_price, it)
        } ?: setPrice(
            mView.min_price,
            mView.price_range.leftIndex + 1
        )
        filter.priceMax?.let { setPrice(mView.max_price, it) } ?: setPrice(
            mView.max_price,
            mView.price_range.rightIndex + 1
        )
        restoreRangeBar()
    }

    private fun restoreRangeBar() {
        val priceMin = filter.priceMin ?: resources.getInteger(R.integer.filter_min_price_default)
        val priceMax = filter.priceMax ?: resources.getInteger(R.integer.filter_max_price_default)
        mView.price_range.setRangePinsByValue(priceMin.toFloat(), priceMax.toFloat())
    }

    private fun setPrice(priceTv: TextView?, price: Int) {
        priceTv?.text = resources.getString(R.string.product_price_eur, price)
    }

    private fun handleArgs() {
        arguments?.let {
            if (it.containsKey(ARG_FILTER)) {
                this.filter = it.getSerializable(ARG_FILTER) as ProductFilter
            }
        }
    }

    override fun onTouchEnded(rangeBar: RangeBar?) {

    }

    override fun onRangeChangeListener(
        rangeBar: RangeBar?,
        leftPinIndex: Int,
        rightPinIndex: Int,
        leftPinValue: String?,
        rightPinValue: String?
    ) {
        updatePriceLabels(leftPinIndex + 1, rightPinIndex + 1)
        updateFilterSettings(leftPinIndex + 1, rightPinIndex + 1)
    }

    private fun updateFilterSettings(priceMin: Int, priceMax: Int) {
        this.filter.priceMin = priceMin
        this.filter.priceMax = priceMax

    }

    override fun onTouchStarted(rangeBar: RangeBar?) {
    }

    private fun updatePriceLabels(priceMin: Int, priceMax: Int) {
        setPrice(mView.min_price, priceMin)
        setPrice(mView.max_price, priceMax)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println("")
    }

    private fun saveFilterChanges() {
        val resultIntent = createResultIntent()
        activity?.setResult(Activity.RESULT_OK, resultIntent)
    }

    private fun createResultIntent(): Intent {
        val resultIntent = Intent()
        resultIntent.putExtra(ARG_FILTER, filter)
        return resultIntent
    }

}
