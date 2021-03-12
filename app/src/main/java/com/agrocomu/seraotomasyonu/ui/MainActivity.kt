package com.agrocomu.seraotomasyonu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agrocomu.seraotomasyonu.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    title=resources.getString(R.string.main_page)
//                    loadFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home2 -> {
                    title=resources.getString(R.string.main_page)
//                    loadFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}