package com.YAHO


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.YAHO.databinding.ActivityMainBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var  binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    override fun onBackPressed() {
        // super.onBackPressed()
    }



}