package com.YAHO


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.YAHO.databinding.ActivityRegisterBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://15.165.28.181:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(RegisterService::class.java)


        binding.registerBtn.setOnClickListener {
            val remail = binding.idEt.toString()
            val rpassword = binding.passwordEt.toString()
            service.Register(Login(remail, rpassword)).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result = response.body()
                    Log.e("회원가입", "${result}")
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("회원가입", "${t.localizedMessage}")
                }
            })
        }
    }
}

interface RegisterService {
    @POST("/Register")
    fun Register(@Body login: Login) : Call<String>
}