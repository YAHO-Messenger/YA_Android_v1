package com.YAHO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.YAHO.databinding.ActivityMainBinding
import com.YAHO.databinding.ActivityRegisterBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var  binding: ActivityMainBinding


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
}