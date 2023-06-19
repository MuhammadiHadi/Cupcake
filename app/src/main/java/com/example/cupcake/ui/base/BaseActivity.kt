package com.example.cupcake.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract  class BaseActivity<T:ViewDataBinding>(val xml:Int):AppCompatActivity() {
  lateinit var binding : T
    override fun onCreate(savedInstanceState : Bundle? , persistentState : PersistableBundle?) {
        super.onCreate(savedInstanceState , persistentState)

        binding=DataBindingUtil.setContentView(this,xml)

    }
    abstract  fun setUi(binding : T)
}