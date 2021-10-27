package com.YAHO

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.YAHO.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityLoginBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //progressDialog
    private lateinit var  progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private  var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("기다려주세요")
        progressDialog.setMessage("로그인중입니다")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.noAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            validateData()
        }

    }

    override fun onBackPressed() {
        // super.onBackPressed()
    }

    private fun validateData() {
        email = binding.idEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.idEt.error = "이메일 형식에 맞게 쓰세요"
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEt.error = "비밀번호를 입력해주세요"
        }
        else{
            firebaseLogin()
        }
    }
    private  fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "환영합니다", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "정보가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                startActivity((Intent(this, LoginActivity::class.java)))
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                Toast.makeText(this,"loggedIn as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
    }

    private fun checkUser() {
        //만약 이미 로그인됬다면 profile로 이동
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser !=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}