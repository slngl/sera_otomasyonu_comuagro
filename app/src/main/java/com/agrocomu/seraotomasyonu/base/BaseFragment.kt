package com.agrocomu.seraotomasyonu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.agrocomu.seraotomasyonu.ui.MainActivity


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected lateinit var binding: DB
    abstract val layoutResource: Int


    var act: MainActivity? = null


    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    fun loadFragment(
        containerId: Int,
        fragment: Fragment,
        fragmentManager: FragmentManager,
        name: String,
        addToBackStack: Boolean
    ) {
        fragmentManager.commit {
            if (addToBackStack) {
                addToBackStack(name)
            }
            replace(containerId, fragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        act = activity as? MainActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        act = null
    }

}