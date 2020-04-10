package com.monkeys.test.view

import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.model.Store

interface StoreSelectionView {
    interface StoreSelectionListener{
        fun onStoreSelected(store: Store)
    }

    val networkOperationCallback: NetworkOperationCallback

    fun showStoreList(stores: Array<Store>)
}