package com.agrocomu.seraotomasyonu.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: DB
    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutResource)
        binding.lifecycleOwner = this
    }

    fun loadFragment(containerId: Int, fragment: Fragment, name: String, addToBackStack: Boolean) {
        supportFragmentManager.commit {
            if (addToBackStack) {
                addToBackStack(name)
            }
            replace(containerId, fragment)
        }
    }
}