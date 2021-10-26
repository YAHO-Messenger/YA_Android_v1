package com.YAHO

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.YAHO.databinding.ActivityLoginBinding
import com.YAHO.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.noAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}