package com.example.cupcake.ui.Fragment


import android.annotation.SuppressLint
import android.os.CountDownTimer
import com.example.cupcake.R
import com.example.cupcake.Utils.Constants.OTP_FORMAT
import com.example.cupcake.Utils.millisToTime
import com.example.cupcake.databinding.FragmentCardMethodBinding
import com.example.cupcake.ui.base.BaseFragment

class CardMethodFragment : BaseFragment<FragmentCardMethodBinding>(R.layout.fragment_card_method) {

    override fun setContentToView(binding : FragmentCardMethodBinding) {
        startTimer(120000)
        binding.apply {
            tvResend.setOnClickListener {
                startTimer(120000)
            }

        }



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

}