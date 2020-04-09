package com.monkeys.test.presenter

import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreSelectionPresenter (val storeSelectionView: StoreSelectionView?){
    fun retrieveStores(){
        storeSelectionView?.showProgress()
        MagentoApiService.create().getStores().enqueue(object : Callback<Array<Store>>{
            override fun onResponse(call: Call<Array<Store>>, response: Response<Array<Store>>) {
                response.body()?.let {
                    storeSelectionView?.showStoreList(it)
                }?: storeSelectionView?.showError()
            }

            override fun onFailure(call: Call<Array<Store>>, t: Throwable) {
                storeSelectionView?.showError()
            }

        })
    }
}