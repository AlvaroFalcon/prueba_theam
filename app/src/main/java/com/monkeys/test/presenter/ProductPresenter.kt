package com.monkeys.test.presenter

import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.model.Product
import com.monkeys.test.model.SearchResultResponse
import com.monkeys.test.model.filter.ProductFilter
import com.monkeys.test.view.ProductListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(val productListView: ProductListView?) {
    var productList : Array<Product> = arrayOf()

    fun initView(productFilter: ProductFilter){
        if(productList.isNotEmpty()){
            productListView?.showProducts(productList)
        }else retrieveProducts(productFilter)
    }

    fun retrieveProducts(productFilter: ProductFilter) {
        productListView?.networkOperationCallback?.showProgress()
        MagentoApiService.create()
            .getProducts(
                productFilter.storeId,
                productFilter.categoryId,
                productFilter.order?.orderBy?.value,
                productFilter.order?.orderDir?.value,
                productFilter.text,
                productFilter.page,
                productFilter.limit
            )
            .enqueue(object: Callback<SearchResultResponse>{
                override fun onResponse(
                    call: Call<SearchResultResponse>,
                    response: Response<SearchResultResponse>
                ) {
                    val body = response.body()
                    if(body != null && response.isSuccessful){
                        productList = body.results
                        productListView?.showProducts(body.results)
                    }else{
                        productListView?.networkOperationCallback?.showError()
                    }
                }

                override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                    productListView?.networkOperationCallback?.showError()
                }

            })
    }
}