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
import com.example.cupcake.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
   private  var _binding:FragmentLoginBinding?=null
    private val binding get()=_binding!!
    private var  userName=""
    private var  userEmail=""
    private var  userPassword=""
    lateinit var sqliteHelper : SqliteHelper
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
       sqliteHelper= SqliteHelper(requireContext())
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.tvNext.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnLogin.setOnClickListener {
            userEmail=binding.email.text.toString().trim()
            userPassword=binding.password.text.toString().trim()
            binding.prograssbar.visibility=View.VISIBLE
            binding.tvLoginView.visibility=View.INVISIBLE
            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
                val user = sqliteHelper.getUser(userEmail)
                if (user != null && user.password == userPassword) {
                    binding.prograssbar.visibility=View.INVISIBLE
                    binding.tvLoginView.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                  findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    binding.prograssbar.visibility=View.INVISIBLE
                    binding.tvLoginView.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.prograssbar.visibility=View.INVISIBLE
                binding.tvLoginView.visibility=View.VISIBLE
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


}