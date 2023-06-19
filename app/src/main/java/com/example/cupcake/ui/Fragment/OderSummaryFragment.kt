package com.example.cupcake.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcake.MainActivity
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentHomeBinding
import com.example.cupcake.databinding.FragmentOderSummaryBinding
import com.example.cupcake.ui.ShareViewModel.ShareViewModel


class OderSummaryFragment : Fragment() {
    private  var _binding: FragmentOderSummaryBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel:ShareViewModel
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding= FragmentOderSummaryBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
           findNavController().navigate(R.id.action_orderSummaryFragment_to_cardMethodFragment)
        }
        binding.btnCancelTwo.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.apply {
            tvQuantity.text=viewModel.quantity.value.toString()
            tvRopy.text=viewModel.priceText.value.toString()
            tvPickup.text=viewModel.pickupDate.value.toString()
            tvFlavor.text=viewModel.flavor.value.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title="Order Summary"
    }

}