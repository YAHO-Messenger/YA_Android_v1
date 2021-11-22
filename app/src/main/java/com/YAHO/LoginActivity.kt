package com.YAHO


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.YAHO.databinding.ActivityLoginBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class LoginActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noAccount.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://15.165.28.181:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service = retrofit.create(SignService::class.java)


        binding.loginBtn.setOnClickListener {

            val email = binding.idEt.text.toString()
            val password = binding.passwordEt.text.toString()
            service.login(Login(email, password)).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result = response.body()
                    Log.e("로그인", "${result}")
                }

                override fun onFailure(call: Call<String>, t:Throwable) {
                    Log.e("로그인", "${t.localizedMessage}")
                }
            })
        }
    }
}

interface SignService {
    @POST("/login")
    fun login(@Body login: Login) : Call<String>
}
