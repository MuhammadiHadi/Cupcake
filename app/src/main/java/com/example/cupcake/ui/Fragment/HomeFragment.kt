package com.example.cupcake.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentChooseFlavorBinding
import com.example.cupcake.databinding.FragmentHomeBinding
import com.example.cupcake.ui.ShareViewModel.ShareViewModel

class HomeFragment : Fragment() {
    private  var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel:ShareViewModel
    private var quantityOne:Int=0
    private var quantityTow:Int=0
    private var quantityThree:Int=0
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding=  FragmentHomeBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)

        binding.apply {
            btnOrderOne.setOnClickListener {
                viewModel.quantity(1)
                findNavController().navigate(R.id.action_homeFragment_to_chooseFlavorFragment)
            }
            btnOrderTwo.setOnClickListener {
                viewModel.quantity(6)
                findNavController().navigate(R.id.action_homeFragment_to_chooseFlavorFragment)
            }
            btnOrderMore.setOnClickListener {
                viewModel.quantity(12)
                findNavController().navigate(R.id.action_homeFragment_to_chooseFlavorFragment)
            }
        }


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title="Cupcake"
    }

}