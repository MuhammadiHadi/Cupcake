package com.example.cupcake.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.SQLite.SqliteHelper
import com.example.cupcake.Utils.SessionManager
import com.example.cupcake.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {
    private  var _binding: FragmentUserProfileBinding?=null
    private val binding get()=_binding!!

    lateinit var sqliteHelper : SqliteHelper
    lateinit var sessionManager : SessionManager
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding= FragmentUserProfileBinding.inflate(inflater,container,false)
       sqliteHelper= SqliteHelper(requireContext())
        sessionManager=SessionManager(requireContext())


        val user = sqliteHelper.getUserById()

        if (user != null) {
            // User data retrieved successfully
            binding.name.setText(user.name)
            binding.email.setText(user.email)
            binding.phone.setText(user.phone)
        } else {
            // User not found in the database or error occurred while retrieving the data
            Toast.makeText(requireContext() , " User not found" , Toast.LENGTH_SHORT).show()
        }
        binding.btnLogout.setOnClickListener {
            sessionManager.logout()
            findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        }
        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_updateFragment)
        }
        return binding.root
    }


}