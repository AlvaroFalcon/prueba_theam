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
import com.appyvet.materialrangebar.RangeBar

import com.monkeys.test.R
import com.monkeys.test.model.filter.FilterOrder
import com.monkeys.test.model.filter.ProductFilter
import com.monkeys.test.view.activities.FilterActivity
import com.monkeys.test.view.activities.FilterActivity.Companion.ARG_FILTER
import kotlinx.android.synthetic.main.fragment_filter.view.*

class FilterFragment : Fragment(), RangeBar.OnRangeBarChangeListener, AdapterView.OnItemSelectedListener {
    lateinit var mView: View
    lateinit var filter: ProductFilter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_filter, container, false)
        mView.price_range.setOnRangeBarChangeListener(this)
        mView.order_by_spinner.onItemSelectedListener = this
        updatePriceLabels(mView.price_range.leftIndex, mView.price_range.rightIndex)
        context?.let {
            val adapter = ArrayAdapter<FilterOrder>(it, android.R.layout.simple_spinner_item, FilterOrder.getFilterOrderList(it))
            mView.order_by_spinner.adapter = adapter
        }
        filter = ProductFilter(storeId = 41, categoryId = 17)
        mView.search_btn.setOnClickListener {
            saveFilterChanges()
            activity?.finish()
        }
        return mView
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
    }

    override fun onTouchStarted(rangeBar: RangeBar?) {
    }

    private fun updatePriceLabels(minPrice: Int, maxPrice: Int) {
        mView.min_price.text = resources.getString(R.string.product_price_eur, minPrice)
        mView.max_price.text = resources.getString(R.string.product_price_eur, maxPrice)
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
