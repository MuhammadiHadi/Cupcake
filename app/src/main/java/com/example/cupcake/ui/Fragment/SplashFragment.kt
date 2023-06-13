package com.example.cupcake.ui.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private  var _binding:FragmentSplashBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
       _binding=FragmentSplashBinding.inflate(inflater,container,false)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
          findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        },3000)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }


}