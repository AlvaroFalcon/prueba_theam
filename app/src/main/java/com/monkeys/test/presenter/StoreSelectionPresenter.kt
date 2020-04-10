package com.monkeys.test.presenter

import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreSelectionPresenter (val storeSelectionView: StoreSelectionView?){
    var storeList : Array<Store>? = null

    fun showStores(){
        storeList?.let {
            storeSelectionView?.showStoreList(it)
        } ?: retrieveStoreList()
    }

    private fun retrieveStoreList(){
        storeSelectionView?.networkOperationCallback?.showProgress()
        MagentoApiService.create().getStores().enqueue(object : Callback<Array<Store>>{
            override fun onResponse(call: Call<Array<Store>>, response: Response<Array<Store>>) {
                response.body()?.let {
                    storeSelectionView?.showStoreList(it)
                }?: storeSelectionView?.networkOperationCallback?.showError()
            }

            override fun onFailure(call: Call<Array<Store>>, t: Throwable) {
                storeSelectionView?.networkOperationCallback?.showError()
            }

        })
    }
}