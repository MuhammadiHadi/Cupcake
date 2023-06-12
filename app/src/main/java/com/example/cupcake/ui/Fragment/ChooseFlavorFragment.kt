package com.example.cupcake.ui.Fragment

import android.os.Bundle
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
import com.example.cupcake.databinding.FragmentChooseFlavorBinding
import com.example.cupcake.databinding.FragmentChoosePickupDateBinding
import com.example.cupcake.ui.ShareViewModel.ShareViewModel


class ChooseFlavorFragment : Fragment() {
    private  var _binding: FragmentChooseFlavorBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel:ShareViewModel

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding=  FragmentChooseFlavorBinding.inflate(inflater,container,false)


        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFlavorFragment_to_choosePickDateFragment)
        }
      binding.btnCancel.setOnClickListener {
          findNavController().popBackStack()
      }

        viewModel = ViewModelProvider(requireActivity())[ShareViewModel::class.java]

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.updateValue(checkedId)
            viewModel.updateFlavor(checkedId)
        }
       

        viewModel.priceText.observe(viewLifecycleOwner, Observer {
            binding.tvPrice.text=it.toString()
        })

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title="Choose Flavor"
    }


}