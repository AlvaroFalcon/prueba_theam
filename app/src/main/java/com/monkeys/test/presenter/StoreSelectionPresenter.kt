package com.monkeys.test.presenter

import android.content.Context
import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.common.ActivityLauncher
import com.monkeys.test.common.PreferenceManager
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreSelectionPresenter (val storeSelectionView: StoreSelectionView?){
    var storeList : Array<Store>? = null

    fun initView(context: Context?){
        if(context != null && PreferenceManager.getStoreId(context) != PreferenceManager.NO_STORE_SELECTED_VALUE){
            ActivityLauncher.launchMainActivity(context)
        }else{
            showStores()
        }
    }

    private fun showStores(){
        storeList?.let {
            storeSelectionView?.showStoreList(it)
        } ?: retrieveStoreList()
    }

    private fun retrieveStoreList(){
        storeSelectionView?.networkOperationCallback?.showProgress()
        MagentoApiService.create().getStores().enqueue(object : Callback<Array<Store>>{
            override fun onResponse(call: Call<Array<Store>>, response: Response<Array<Store>>) {
                val body = response.body()
                if(response.isSuccessful && body != null){
                    this@StoreSelectionPresenter.storeSelectionView?.showStoreList(body)
                }else this@StoreSelectionPresenter.storeSelectionView?.networkOperationCallback?.showError()
            }

            override fun onFailure(call: Call<Array<Store>>, t: Throwable) {
                this@StoreSelectionPresenter.storeSelectionView?.networkOperationCallback?.showError()
            }

        })
    }
}