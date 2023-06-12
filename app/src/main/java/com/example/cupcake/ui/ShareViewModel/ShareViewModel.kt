package com.example.cupcake.ui.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupcake.R
import kotlinx.coroutines.launch

class ShareViewModel:ViewModel() {

    private val _priceText=MutableLiveData<String>()
    val priceText:LiveData<String>
        get() = _priceText

    private val _pickupDate=MutableLiveData<String>()
    val pickupDate:LiveData<String>
        get() = _pickupDate

    private val _quantity=MutableLiveData<String>()
    val quantity:LiveData<String>
        get() = _quantity

    private val _flavor=MutableLiveData<String>()
    val flavor:LiveData<String>
        get() = _flavor


    fun updateFlavor(id:Int){
        viewModelScope.launch {
            val flavor = when (id) {
                R.id.rb_1 -> "Vanilla"
                R.id.rb_2 -> "Chocolate"
                R.id.rb_3 -> "Red Velvet"
                R.id.rb_4 -> "Salted Caramel"
                R.id.rb_5 -> "Coffee"
                else -> ""
            }
            _flavor.value =  flavor
        }

    }
    fun updateValue(id:Int){
       val price = when (id) {
        R.id.rb_1 -> "SubTotal:100$"
        R.id.rb_2 -> "SubTotal:150$"
        R.id.rb_3 -> "SubTotal:200$"
        R.id.rb_4 -> "SubTotal:300$"
        R.id.rb_5 -> "SubTotal:500$"
        else -> ""
    }
    _priceText.value = price
}
    fun pickDate(id:Int){
        val pickDate = when (id) {
            R.id.rb_fir -> "Fir Dec 11"
            R.id.rb_sat -> "Sat Dec 12"
            R.id.rb_sun -> "Sun Dec 13"
            R.id.rb_mon -> "Mon Dec 14"

            else -> ""
        }
        _pickupDate.value = pickDate
    }

    fun quantity(value:Int){
           _quantity.postValue(value.toString())
    }
}
