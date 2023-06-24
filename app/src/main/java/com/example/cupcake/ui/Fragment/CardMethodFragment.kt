package com.example.cupcake.ui.Fragment


import android.R.attr.defaultValue
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.CountDownTimer
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.Utils.Constants.OTP_FORMAT
import com.example.cupcake.Utils.millisToTime
import com.example.cupcake.databinding.FragmentCardMethodBinding
import com.example.cupcake.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*


class CardMethodFragment : BaseFragment<FragmentCardMethodBinding>(R.layout.fragment_card_method) {

    val cal = Calendar.getInstance()
    private var isCheck:Boolean = false


    @SuppressLint("ResourceType")
//    val languages = resources.getStringArray(R.array.list)
//    val adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,languages)
    override fun setContentToView(binding : FragmentCardMethodBinding) {
        startTimer(120000)
        binding.apply {




            tvBundle.text=arguments?.getString("hadi").toString()


            tvResend.setOnClickListener {
                startTimer(120000)
            }
            binding.btnSelect.setOnClickListener {
                if(checkbox.isChecked){
                    isCheck=true
                    Toast.makeText(requireContext() , isCheck.toString() , Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireContext() , isCheck.toString() , Toast.LENGTH_SHORT).show()
                }
                findNavController().navigate(R.id.action_cardMethodFragment_to_complaintFragment)
                Toast.makeText(requireContext() , binding.fieldDob.text.toString() , Toast.LENGTH_SHORT).show()
            }
            isEmail(cardEditText.text.toString())
            cardEditText.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s : CharSequence? ,
                    start : Int ,
                    count : Int ,
                    after : Int
                ) {
                    // This method is empty and does not contain any code.
                }

                override fun onTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!ch.isNullOrEmpty()) {
                        if (ch.toString().length > 3) {
                            // Set the drawable on the end side
                            binding.cardEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.iv_visa,
                                0
                            ) // Replace R.drawable.your_drawable with the actual drawable resource ID
                        }
                       else if(ch.toString().startsWith("4")){
                            // Set the drawable on the end side
                            binding.cardEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.iv_visa,
                                0
                            )
                        }
                        else if(ch.toString().startsWith("6")){
                            // Set the drawable on the end side
                            binding.cardEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.iv_debit,
                                0
                            )
                        }

                        else {
                            // Remove the drawable if the length is not 4
                            binding.cardEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
                        }
                    }

                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })




            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }
            }

            fieldDob.setOnClickListener(object : View.OnClickListener {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onClick(view: View) {
                    val dialogView = LayoutInflater.from(requireContext())
                        .inflate(R.layout.custom_date_picker, null)

                    val datePicker = dialogView.findViewById<DatePicker>(R.id.datePicker)
                    datePicker.init(
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        null
                    )

                    DatePickerDialog(requireContext())
                        .apply {
                            setView(dialogView)
                            setButton(
                                DialogInterface.BUTTON_POSITIVE,getString(R.string.ok)
                            ) { _, _ ->
                                val year = datePicker.year
                                val monthOfYear = datePicker.month
                                val dayOfMonth = datePicker.dayOfMonth
                                dateSetListener.onDateSet(datePicker, year, monthOfYear, dayOfMonth)
                            }
                            setButton(
                                DialogInterface.BUTTON_NEGATIVE,getString(R.string.cancel)
                            ) { dialog, _ ->
                                dialog.cancel()
                            }
                        }
                        .show()
                }
            })


        }




    }
    private fun updateDateInView() {
        var myFormat = "MM/dd/yyyy" // mention the format you need
        var sdf = SimpleDateFormat(myFormat, Locale.US)
        val formattedDate= sdf.format(cal.time)
        val editable: Editable = SpannableStringBuilder.valueOf(formattedDate)
       binding.fieldDob.text = editable
    }

    private var cTimer: CountDownTimer? = null
    private fun startTimer(time: Long) {
        binding.tvResend.isClickable = false
        cTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResend.text = millisToTime(OTP_FORMAT, millisUntilFinished)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.tvResend.apply {
                    isClickable = true
                    text = "Resent"
                }
            }
        }
        (cTimer as CountDownTimer).start()
    }

   fun isEmail(email:String):Boolean{
       return  email.length==16

   }


}