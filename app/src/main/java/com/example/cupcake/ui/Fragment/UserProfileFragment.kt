package com.example.cupcake.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentLoginBinding
import com.example.cupcake.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {
    private  var _binding: FragmentUserProfileBinding?=null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding= FragmentUserProfileBinding.inflate(inflater,container,false)
        return binding.root
    }


}