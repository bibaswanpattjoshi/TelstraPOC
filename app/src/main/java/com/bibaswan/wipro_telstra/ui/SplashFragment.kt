package com.bibaswan.wipro_telstra.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bibaswan.wipro_telstra.R
import com.bibaswan.wipro_telstra.databinding.SplashFragmentBinding
import com.bibaswan.wipro_telstra.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val SPLASH_TIME_OUT:Long=3500
    private var binding: SplashFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            findNavController().navigate(
                R.id.action_splashFragment_to_charactersFragment)
        }, SPLASH_TIME_OUT)
    }

}
