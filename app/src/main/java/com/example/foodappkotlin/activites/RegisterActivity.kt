package com.example.foodappkotlin.activites

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodappkotlin.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        // click đăng ký
        binding.btnregister.setOnClickListener {
            register()
        }
    }

    private fun register() {
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

        mAuth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Tạo tài khoản thành công !", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tạo tài khoản không thành công !", Toast.LENGTH_SHORT).show()

            }

        }

    }
}