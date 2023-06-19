package com.example.cupcake.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class Nextbase<T:ViewDataBinding>(val xml :Int) :Fragment(){

    lateinit var binding:T

    override fun onCreateView(
        inflater : LayoutInflater ,
        container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {
        if(!::binding.isInitialized){
            binding=DataBindingUtil.inflate(inflater,xml,container,false)
        }
        return binding.root
    }
    abstract  fun setContentToView(binding : T)


}