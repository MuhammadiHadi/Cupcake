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
import com.example.cupcake.R
import com.example.cupcake.SQLite.SqliteHelper
import com.example.cupcake.Utils.SessionManager
import com.example.cupcake.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginFragment : Fragment() {
   private  var _binding:FragmentLoginBinding?=null
    private val binding get()=_binding!!
    private var  userName=""
    private var  userEmail=""
    private var  userPassword=""
    lateinit var sqliteHelper : SqliteHelper
    @SuppressLint("ClickableViewAccessibility")
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


      binding.apply {
          btnLogin.setOnClickListener {
              validateForm()
          }
                 email .setOnTouchListener { v , event ->
                     showError(edEmail)
                     false
                 }

          password.setOnTouchListener { v , event ->
              showError(edPassword)
              false
          }
      }




//            userEmail=binding.email.text.toString().trim()
//            userPassword=binding.password.text.toString().trim()




//            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
//                val user = sqliteHelper.getUser(userEmail)
//                if (user != null && user.password == userPassword) {
//
//                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
//                  findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                } else {
//
//                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//
//                Toast.makeText(requireContext(), "Please enter username and password", Toast.LENGTH_SHORT).show()
//            }
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
    private fun showError(layout: TextInputLayout , error: String? = null) {
        layout.errorIconDrawable = null
        layout.error = error
        layout.isErrorEnabled = error != null
    }

    private fun validateForm() {
        var isFormFilledProperly = true
        binding.apply {
            if (isEmail(email.text.toString())) {
                showError(edEmail)
            } else {
                isFormFilledProperly = false
                showError(edEmail ,  "Enter validate email")
            }
            if (isPassword(password.text.toString())) {
                showError(edPassword)
            } else {
                isFormFilledProperly = false
                showError(edPassword , "wrong password")
            }
            if(isFormFilledProperly){
                val user = sqliteHelper.getUser(binding.email.text.toString())
                if (user != null && user.password ==binding. password.text.toString()) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                  findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {

                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }     private fun isEmail(email: String?): Boolean {
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
    private fun isPassword(password: String?): Boolean {
        password?.let {
            val pattern: Pattern
            val PASSWORD_PATTERN = "^\\d{8}$"
            pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher: Matcher = pattern.matcher(password)
            return matcher.matches()
        }
        return false
    }

    private fun isPhone(phone:String?):Boolean{

    return  phone!!.length==9 && phone[0]=='3'
    }



}