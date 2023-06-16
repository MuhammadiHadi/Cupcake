package com.example.cupcake.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentUpdateUserProfileBinding

class UpdateUserProfileFragment : Fragment() {
     private var _binding:FragmentUpdateUserProfileBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding= FragmentUpdateUserProfileBinding.inflate(inflater,container,false)
        binding.updateBtn.setOnClickListener {
            val name=binding.edText.text.toString().trim()
            if(name.isNotEmpty()){
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext() , "enter Text" , Toast.LENGTH_SHORT).show()
            }


        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar!!.title="Update userProfile"
    }

}