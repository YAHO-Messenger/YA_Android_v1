package com.YAHO


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.YAHO.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var  binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}