package com.YAHO

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.YAHO.databinding.ActivityMainBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var  binding: ActivityMainBinding

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(FreeFragment.newInstance())
        binding.bottomNavigation.show(0)

        binding.bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_baseline_developer_mode_24))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_round_fastfood_24))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_baseline_whatshot_24))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_baseline_sports_esports_24))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_outline_movie_24))

        binding.bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(DevelopFragment.newInstance())
                }
                1 -> {
                    replaceFragment(FoodFragment.newInstance())
                }
                2 -> {
                    replaceFragment(FreeFragment.newInstance())
                }
                3 -> {
                    replaceFragment(GameFragment.newInstance())
                }
                4-> {
                    replaceFragment(MovieFragment.newInstance())
                }
            }
        }
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

    }


    override fun onBackPressed() {
        // super.onBackPressed()
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
    private fun addFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            val email = firebaseUser.email
            binding.emailTv.text = email
        }
        else{
            startActivity((Intent(this, LoginActivity::class.java)))
        }

    }

}