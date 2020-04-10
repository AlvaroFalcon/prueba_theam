package com.monkeys.test.view

import com.monkeys.test.model.StoreView

interface LanguageSelectionView{
    interface LanguageSelectionListener{
        fun onLanguageSelected(storeView: StoreView)
    }
    fun showLanguages(storeViews: Array<StoreView>)
}