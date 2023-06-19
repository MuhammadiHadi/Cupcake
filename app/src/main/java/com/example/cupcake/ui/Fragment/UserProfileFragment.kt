package com.example.cupcake.ui.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.SQLite.SqliteHelper
import com.example.cupcake.Utils.SessionManager
import com.example.cupcake.databinding.FragmentUserProfileBinding
import com.example.cupcake.ui.base.BaseFragment

class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>(R.layout.fragment_user_profile) {
    lateinit var sqliteHelper : SqliteHelper
    lateinit var sessionManager : SessionManager

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)

    }
    override fun setContentToView(binding : FragmentUserProfileBinding) {
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
    }
}