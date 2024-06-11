package com.example.foodappkotlin.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.foodappkotlin.R
import com.example.foodappkotlin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
        initListener()


    }

    private fun initData() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initView() {

    }

    private fun initListener() {
        //click login
        binding.btnlogin.setOnClickListener {
            login()
        }
        //click register
        binding.btnregister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val email: String = binding.email.text.toString()
        val pass: String = binding.password.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "vui long nhap Email !", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "vui long nhap mat khau !", Toast.LENGTH_SHORT).show()
            return
        }
        mAuth!!.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                intent = Intent(this, MainActivity::class.java)
                intent.putExtra(email,"EMAIL")
                intent.putExtra(pass,"PASS")
                startActivity(intent)
            } else {
                Toast.makeText(this, "Đăng nhập không thành công !", Toast.LENGTH_SHORT).show()
            }
        }

    }
}