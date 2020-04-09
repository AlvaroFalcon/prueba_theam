package com.monkeys.test.view

import com.monkeys.test.model.Store

interface StoreSelectionView {
    interface StoreSelectionListener{
        fun onStoreSelected(store: Store)
    }
    fun showProgress()
    fun showStoreList(stores: Array<Store>)
    fun hideProgress()
    fun showError()
}