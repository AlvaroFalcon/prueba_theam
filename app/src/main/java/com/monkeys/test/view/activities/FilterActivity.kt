package com.monkeys.test.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.R
import com.monkeys.test.view.fragments.FilterFragment

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FilterFragment()).commit()
    }
}
