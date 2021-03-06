package com.monkeys.test.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.monkeys.test.R
import com.monkeys.test.common.ActivityLauncher
import com.monkeys.test.model.Category
import com.monkeys.test.model.Product
import com.monkeys.test.model.filter.ProductFilter
import com.monkeys.test.view.CategoryView
import com.monkeys.test.view.ProductListView
import com.monkeys.test.view.fragments.CategoryFragment
import com.monkeys.test.view.fragments.ProductListFragment

class MainActivity : AppCompatActivity(), CategoryView.CategorySelectionListener, ProductListView.ProductSelectionListener,
    FragmentManager.OnBackStackChangedListener {
    override fun onBackStackChanged() {
        updateNavBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.addOnBackStackChangedListener(this)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CategoryFragment.newInstance()).commit()
        }

    }

    override fun onCategorySelected(category: Category) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductListFragment.newInstance(category))
            .addToBackStack("${ProductListFragment::class.java.name}${category.categoryId}")
            .commit()
    }

    private fun updateNavBackButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
    }

    override fun onProductSelected(product: Product) {
        ActivityLauncher.launchProductDetailActivity(this, product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_change_store ->{
                ActivityLauncher.launchStoreSelectionActivity(this, true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_filter_list).apply { isVisible = false }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    private fun getCurrentFragment(): Fragment?{
        return supportFragmentManager.findFragmentById(R.id.fragment_container)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == FilterActivity.FILTER_REQUEST_CODE && data != null){
            val fragment = getCurrentFragment()
            if(fragment is ProductListView){
                applyFilter(getProductFilter(data), fragment)
            }
        }
    }

    private fun applyFilter(filter: ProductFilter, productListView: ProductListView) {
        productListView.applyFilter(filter)
    }

    private fun getProductFilter(data: Intent) =
        data.getSerializableExtra(FilterActivity.ARG_FILTER) as ProductFilter

}
