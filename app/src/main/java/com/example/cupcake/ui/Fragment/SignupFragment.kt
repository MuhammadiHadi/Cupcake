package com.example.cupcake.ui.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.cupcake.SQLite.SqliteHelper
import com.example.cupcake.databinding.FragmentSignupBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupFragment : Fragment() {
  private var _binding:FragmentSignupBinding?=null
    private val binding get() = _binding!!
    private var  phone=""
    lateinit var sqliteHelper : SqliteHelper


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View {
       _binding=FragmentSignupBinding.inflate(inflater,container,false)
        sqliteHelper=SqliteHelper(requireContext())
       binding.btnBack.setOnClickListener {
           findNavController().popBackStack()
       }
        binding.apply {
        button.setOnClickListener {
            formValidation()
        }
            email.setOnTouchListener { v, event ->
                showError(edEmail)
                false
            }
            edUsername.setOnTouchListener { v, event ->
                showError(edName)
                false
            }
        }
        binding.button.setOnClickListener {
//            userName=binding.edUsername.text.toString().trim()
//            userEmail=binding.email.text.toString().trim()
//            userPassword=binding.password.text.toString().trim()
//            phone=binding.fieldMobileNo.text.toString().trim()
            formValidation()
//
//            if(userEmail.isNotEmpty()&& userName.isNotEmpty()&&userPassword.isNotEmpty()){
//                isPhone()
//                val userId = sqliteHelper.addUser(userName,userEmail,userPassword,phone)
//                if (userId != -1L) {
//                    Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(requireContext(), "Failed to register user", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(requireContext(), "Please enter username and password", Toast.LENGTH_SHORT).show()
//            }
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
    private  fun formValidation() {
        var isFormValid=true
        binding.apply {
            if(isEmail(email.text.toString())) {
                showError(edEmail)
            } else{
                isFormValid=false
                showError(edEmail,"enter Email")
            }
            if (isFullName(edUsername.text.toString())) {
                showError(edName)
            } else {
                isFormValid=false
                showError(edName , "enter Name")
            }
            if(isFormValid){
                val userId = sqliteHelper.addUser(edUsername.text.toString(),email.text.toString(), password.text.toString(),phone.toString())
                if (userId != -1L) {
                    Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to register user", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun showError(layout: TextInputLayout , error: String? = null) {
        layout.errorIconDrawable = null
        layout.error = error
        layout.isErrorEnabled = error != null
    }
    private fun isEmail(email:String):Boolean{
        email?.let {
            val pattern: Pattern
            val EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher: Matcher = pattern.matcher(email)
            return matcher.matches()
        }
        return false
    }
    private fun isFullName(str:String):Boolean{
        val expression = Regex("^[a-zA-Z\\s]+")
        return str.matches(expression)

    }
}