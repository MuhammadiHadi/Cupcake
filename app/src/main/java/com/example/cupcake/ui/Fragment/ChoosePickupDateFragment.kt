package com.example.cupcake.ui.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcake.MainActivity
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentChoosePickupDateBinding
import com.example.cupcake.databinding.FragmentSplashBinding
import com.example.cupcake.ui.ShareViewModel.ShareViewModel

class ChoosePickupDateFragment: Fragment() {
    private  var _binding: FragmentChoosePickupDateBinding?=null
    private val binding get() = _binding!!
    lateinit var sharedViewModel : ShareViewModel
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup?,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding=  FragmentChoosePickupDateBinding.inflate(inflater,container,false)
        sharedViewModel= ViewModelProvider(requireActivity())[ShareViewModel::class.java]
        binding.apply {
            btnFrwd.setOnClickListener {
                findNavController().navigate(R.id.action_choosePickDateFragment_to_orderSummaryFragment)
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }





        }
        binding.rgRadio.setOnCheckedChangeListener { group, checkedId ->
            sharedViewModel.pickDate(checkedId)
        }
        binding.tvTotal.text=sharedViewModel.priceText.value.toString()




        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title="Choose Pick Date"
    }


}