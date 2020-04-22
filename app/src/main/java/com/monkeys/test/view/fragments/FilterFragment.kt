package com.monkeys.test.view.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class FilterFragment : BaseFragment(), RangeBar.OnRangeBarChangeListener,
    AdapterView.OnItemSelectedListener, TextWatcher {

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
        navTitle = getString(R.string.filter_list)
        super.onCreateView(inflater, container, savedInstanceState)
        mView = inflater.inflate(R.layout.fragment_filter, container, false)
        handleArgs(savedInstanceState)
        initSpinnerAdapter()
        restoreFilterSettings()
        initListeners()
        return mView
    }

    private fun initSpinnerAdapter() {
        context?.let {
            val adapter = ArrayAdapter<FilterOrder>(
                it,
                android.R.layout.simple_spinner_item,
                FilterOrder.getFilterOrderList(it)
            )
            mView.order_by_spinner.adapter = adapter
        }
    }

    private fun initListeners() {
        mView.order_by_spinner.onItemSelectedListener = this
        mView.search_field_input.addTextChangedListener(this)
        /*mView.search_btn.setOnClickListener {
            saveFilterChanges()
            activity?.finish()
        }*/
    }

    private fun restoreFilterSettings() {
        restoreTextSetting()
        restoreOrderSetting()
    }

    private fun restoreOrderSetting() {
        context?.let {
            val order = filter.order ?: FilterOrder.getDefaultOrder(it)
            val items = FilterOrder.getFilterOrderList(it)
            val positionToSelect = FilterOrder.getPosition(order, items)
            mView.order_by_spinner.setSelection(positionToSelect)
        }
    }

    private fun restoreTextSetting() {
        filter.text?.let {
            mView.search_field_input.setText(it)
        }
    }

    private fun setPrice(priceTv: TextView?, price: Int) {
        priceTv?.text = resources.getString(R.string.product_price_eur, price)
    }

    private fun handleArgs(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            restoreFilterFromSavedInstance(savedInstanceState)
        }else{
            restoreFilterFromArguments()
        }
    }

    private fun restoreFilterFromSavedInstance(savedInstanceState: Bundle) {
        this.filter = savedInstanceState.getSerializable(ARG_FILTER) as ProductFilter
    }

    private fun restoreFilterFromArguments() {
        arguments?.let {
            if (it.containsKey(ARG_FILTER)) {
                this.filter = it.getSerializable(ARG_FILTER) as ProductFilter
            }
        }
    }

    override fun onTouchEnded(rangeBar: RangeBar?) {
        //nothing to do
    }

    override fun onRangeChangeListener(
        rangeBar: RangeBar?,
        leftPinIndex: Int,
        rightPinIndex: Int,
        leftPinValue: String?,
        rightPinValue: String?
    ) {
    }

    override fun onTouchStarted(rangeBar: RangeBar?) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        filter.order = mView.order_by_spinner.selectedItem as FilterOrder?
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

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        this.filter.text = text.toString()
    }

    override fun afterTextChanged(p0: Editable?) {
        //nothing to do
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //nothing to do
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ARG_FILTER, filter)
    }
}
