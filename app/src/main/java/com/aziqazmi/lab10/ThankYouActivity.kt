package com.aziqazmi.lab10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aziqazmi.lab10.databinding.ActivityThankYouBinding

class ThankYouActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThankYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThankYouBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGotoPortal.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}