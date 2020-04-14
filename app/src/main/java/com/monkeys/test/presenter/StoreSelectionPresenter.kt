package com.monkeys.test.presenter

import android.content.Context
import android.os.Bundle
import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.common.ActivityLauncher
import com.monkeys.test.common.PreferenceManager
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import com.monkeys.test.view.fragments.StoreSelectionFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreSelectionPresenter(val storeSelectionView: StoreSelectionView?) {
    var storeList: Array<Store>? = null

    fun initView(arguments: Bundle?, context: Context?) {
        if (shouldStartMainActivity(context, arguments)) {
            context?.let {
                ActivityLauncher.launchMainActivity(it)
            }
        } else {
            showStores()
        }
    }

    private fun shouldStartMainActivity(
        context: Context?,
        arguments: Bundle?
    ): Boolean {
        return context != null && PreferenceManager.getStoreId(context) != PreferenceManager.NO_STORE_SELECTED_VALUE && getChangeLanguageArg(
            arguments
        )
    }

    private fun getChangeLanguageArg(arguments: Bundle?): Boolean {
        arguments?.let {
            return !arguments.getBoolean(
                StoreSelectionFragment.EXTRA_CHANGE_LANGUAGE,
                false
            )
        } ?: return false
    }


    private fun showStores() {
        storeList?.let {
            storeSelectionView?.showStoreList(it)
        } ?: retrieveStoreList()
    }

    private fun retrieveStoreList() {
        storeSelectionView?.networkOperationCallback?.showProgress()
        MagentoApiService.create().getStores().enqueue(object : Callback<Array<Store>> {
            override fun onResponse(call: Call<Array<Store>>, response: Response<Array<Store>>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    this@StoreSelectionPresenter.storeSelectionView?.showStoreList(body)
                } else this@StoreSelectionPresenter.storeSelectionView?.networkOperationCallback?.showError()
            }

            override fun onFailure(call: Call<Array<Store>>, t: Throwable) {
                this@StoreSelectionPresenter.storeSelectionView?.networkOperationCallback?.showError()
            }

        })
    }
}


