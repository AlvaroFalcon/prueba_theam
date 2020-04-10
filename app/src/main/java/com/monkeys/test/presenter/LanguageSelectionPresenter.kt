package com.monkeys.test.presenter

import android.os.Bundle
import com.monkeys.test.model.StoreView
import com.monkeys.test.view.LanguageSelectionView
import com.monkeys.test.view.fragments.LanguageSelectionFragment

class LanguageSelectionPresenter(
    private val languageSelectionView: LanguageSelectionView?,
    var storeViews: Array<StoreView> = arrayOf()
) {

    fun handleArgs(arguments: Bundle?) {
        arguments?.apply {
            if (containsKey(LanguageSelectionFragment.ARG_STORE_VIEW)) {
                this@LanguageSelectionPresenter.storeViews = getParcelableArray(LanguageSelectionFragment.ARG_STORE_VIEW) as Array<StoreView>
                this@LanguageSelectionPresenter.showList()
            }
        }
    }

    private fun showList() {
        this.languageSelectionView?.showLanguages(storeViews)
    }
}