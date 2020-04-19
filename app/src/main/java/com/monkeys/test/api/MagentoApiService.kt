package com.monkeys.test.api

import com.monkeys.test.model.Category
import com.monkeys.test.model.SearchResultResponse
import com.monkeys.test.model.Store
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MagentoApiService{
    companion object{
        const val baseUrl = "http://private-anon-3ee2b08f6b-gocco.apiary-mock.com"

        fun create(): MagentoApiService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(MagentoApiService::class.java)
        }
    }

    @GET("stores")
    fun getStores(): Call<Array<Store>>

    @GET("stores/{storeId}/categories")
    fun getCategories(@Path("storeId") storeId: Int): Call<Array<Category>>

    @GET("stores/{storeId}/products/search")
    fun getProducts(@Path("storeId") storeId: Int,
                    @Query("category_id") categoryId: Int,
                    @Query("order") orderBy: String? = "",
                    @Query("dir") dir: String? = "",
                    @Query("with_text") text: String? = "",
                    @Query("page") page: Int,
                    @Query("limit") limit: Int
                    ): Call<SearchResultResponse>

}