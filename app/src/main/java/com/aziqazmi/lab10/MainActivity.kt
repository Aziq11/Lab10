package com.aziqazmi.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aziqazmi.lab10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.login.setOnClickListener {
            val intent = (Intent(this, LoginActivity::class.java))
            startActivity(intent)
        }
        binding.newAccount.setOnClickListener {
            val intent = (Intent(this, NewAccountActivity::class.java))
            startActivity(intent)
        }
    }
}