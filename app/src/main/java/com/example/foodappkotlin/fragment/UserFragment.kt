package com.example.foodappkotlin.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.foodappkotlin.activites.LoginActivity
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.databinding.FragmentUserBinding
import com.example.foodappkotlin.di.mainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.system.exitProcess

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private var nameUser : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(LayoutInflater.from(requireContext()))
        initData()
        initView()
        initListener()
        return binding.root
    }

    private fun initData() {

    }

    private fun initView() {
    }


    private fun initListener() {
        binding.logout.setOnClickListener {

            val logout = MaterialAlertDialogBuilder(requireContext())
            logout.setTitle("Đăng xuất").setMessage("bạn muốn đăng xuất")
                .setPositiveButton("đúng") { _, _ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    exitProcess(1)
                }.setNegativeButton("không") { dialog, _ ->
                    dialog.dismiss()
                }

            val customDialog = logout.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)

        }
    }

}