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
import com.example.cupcake.SQLite.SqliteHelper
import com.example.cupcake.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
  private var _binding:FragmentSignupBinding?=null
    private val binding get() = _binding!!
    private var  userName=""
    private var  userEmail=""
    private var  userPassword=""
    private var  phone=""
    lateinit var sqliteHelper : SqliteHelper

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
       _binding=FragmentSignupBinding.inflate(inflater,container,false)
        sqliteHelper=SqliteHelper(requireContext())
       binding.btnBack.setOnClickListener {
           findNavController().popBackStack()
       }
        binding.button.setOnClickListener {
            userName=binding.edUsername.text.toString().trim()
            userEmail=binding.email.text.toString().trim()
            userPassword=binding.password.text.toString().trim()
            phone=binding.fieldMobileNo.text.toString().trim()


            if(userEmail.isNotEmpty()&& userName.isNotEmpty()&&userPassword.isNotEmpty()){
                isPhone()
                val userId = sqliteHelper.addUser(userName,userEmail,userPassword,phone)
                if (userId != -1L) {
                    Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to register user", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        (activity as AppCompatActivity).supportActionBar!!.show()
        super.onStop()
    }
    private fun isPhone():Boolean{

        return  phone.length==10 && phone[0]=='3'
    }



}