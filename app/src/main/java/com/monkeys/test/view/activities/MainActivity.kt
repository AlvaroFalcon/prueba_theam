package com.monkeys.test.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.R
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import com.monkeys.test.view.fragments.StoreSelectionFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, StoreSelectionFragment())
            .commit()
    }
}
