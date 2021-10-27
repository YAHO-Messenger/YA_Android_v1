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
import com.YAHO.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var  binding: ActivityRegisterBinding

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var  firebaseAuth : FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("기다려주세요")
        progressDialog.setMessage("계정을 생성하는 중입니다")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerBtn.setOnClickListener {
            validData()
        }
    }

    private fun validData() {
        email = binding.idEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.idEt.error = "이메일 형식에 맞게 써주세요"
        }
        else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "비밀번호는 최소 6자 이상이어야합니다"
        }
        else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp(){
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "이메일로 계정이 생성되었습니다 $email", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this, "이미 계정이 있습니다 ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}