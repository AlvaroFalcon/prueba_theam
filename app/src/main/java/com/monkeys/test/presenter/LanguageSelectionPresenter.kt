package com.monkeys.test.presenter

import com.monkeys.test.model.StoreView
import com.monkeys.test.view.LanguageSelectionView

class LanguageSelectionPresenter(private val languageSelectionView: LanguageSelectionView?, var storeViews: Array<StoreView> = arrayOf()){

    fun showList(){
        this.languageSelectionView?.showLanguages(storeViews)
    }
}