package com.agrocomu.seraotomasyonu.ui.fragment

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.agrocomu.seraotomasyonu.R
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        lifecycleScope.launch {
//            delay(2000)
            val anim = view?.findViewById<LottieAnimationView>(R.id.animFixture)
            anim?.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                }

                override fun onAnimationEnd(animator: Animator) {
                    // Hide the Lottie AnimationView
                    findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            //if user did not login
//            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            //else
        }
        return view
    }


}