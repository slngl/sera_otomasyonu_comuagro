package com.agrocomu.seraotomasyonu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseActivity
import com.agrocomu.seraotomasyonu.databinding.ActivityMainBinding
import com.agrocomu.seraotomasyonu.ui.fragment.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        /*bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    title="Login"
                    loadFragment(R.id.container1, addToBackStack = false, fragment = LoginFragment(), name = "Login")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home2 -> {
                    title=resources.getString(R.string.main_page)
//                    loadFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }*/
    }

    override val layoutResource: Int
        get() = R.layout.activity_main
}