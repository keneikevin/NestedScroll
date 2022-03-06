package com.example.nestedscroll.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.ActivityCartyBinding

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
                as NavHostFragment

        navHostFragment.findNavController()
    }}