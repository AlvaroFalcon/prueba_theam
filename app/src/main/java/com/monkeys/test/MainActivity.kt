package com.monkeys.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.api.MagentoApiService
import com.monkeys.test.model.Category
import com.monkeys.test.model.Product
import com.monkeys.test.model.SearchResultResponse
import com.monkeys.test.model.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
