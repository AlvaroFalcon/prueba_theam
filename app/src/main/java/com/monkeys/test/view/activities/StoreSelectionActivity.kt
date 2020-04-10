package com.monkeys.test.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.R
import com.monkeys.test.model.Store
import com.monkeys.test.model.StoreView
import com.monkeys.test.view.LanguageSelectionView
import com.monkeys.test.view.StoreSelectionView
import com.monkeys.test.view.fragments.LanguageSelectionFragment
import com.monkeys.test.view.fragments.StoreSelectionFragment

class StoreSelectionActivity : AppCompatActivity(), StoreSelectionView.StoreSelectionListener, LanguageSelectionView.LanguageSelectionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_selection)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StoreSelectionFragment())
                .commit()
        }
    }

    override fun onStoreSelected(store: Store) {
        val fragment = LanguageSelectionFragment.newInstance(store.storeViews)
        val key = LanguageSelectionFragment::class.java.name
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(key)
            .commit()
    }

    override fun onLanguageSelected(storeView: StoreView) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
