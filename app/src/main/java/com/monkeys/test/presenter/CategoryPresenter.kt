package com.monkeys.test.presenter

import android.content.Context
import android.os.Bundle
import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.common.PreferenceManager
import com.monkeys.test.model.Category
import com.monkeys.test.view.CategoryView
import com.monkeys.test.view.fragments.CategoryFragment.Companion.ARG_CATEGORY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(
    val categoryView: CategoryView?,
    val networkOperationCallback: NetworkOperationCallback? = null
) {

    fun handleArgs(arguments: Bundle?, context: Context?) {
        arguments?.let {
            if (it.containsKey(ARG_CATEGORY)) {
                this@CategoryPresenter.categoryView?.showCategories(
                    it.getParcelableArray(
                        ARG_CATEGORY
                    ) as Array<Category>
                )
            } else if (context != null) getCategories(context) else networkOperationCallback?.showError()
        } ?: if (context != null) getCategories(context)
    }

    private fun getCategories(context: Context) {
        this.networkOperationCallback?.showProgress()
        MagentoApiService.create()
            .getCategories(PreferenceManager.getStoreId(context))
            .enqueue(object : Callback<Array<Category>> {
                override fun onResponse(
                    call: Call<Array<Category>>,
                    response: Response<Array<Category>>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        this@CategoryPresenter.categoryView?.showCategories(body)
                    } else this@CategoryPresenter.networkOperationCallback?.showError()
                }

                override fun onFailure(call: Call<Array<Category>>, t: Throwable) {
                    this@CategoryPresenter.networkOperationCallback?.showError()
                }

            })
    }

}