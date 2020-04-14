package com.monkeys.test.common
import android.content.Context
import android.content.Intent
import com.monkeys.test.model.Product
import com.monkeys.test.view.activities.MainActivity
import com.monkeys.test.view.activities.ProductDetailActivity
import com.monkeys.test.view.activities.StoreSelectionActivity
import com.monkeys.test.view.fragments.StoreSelectionFragment

class ActivityLauncher {

    companion object {

        fun launchMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

        fun launchProductDetailActivity(context: Context, product: Product){
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }

        fun launchStoreSelectionActivity(context: Context, allowChangeStore: Boolean){
            val intent = Intent(context, StoreSelectionActivity::class.java)
            intent.putExtra(StoreSelectionFragment.EXTRA_CHANGE_LANGUAGE, allowChangeStore)
            context.startActivity(intent)
        }

    }
}