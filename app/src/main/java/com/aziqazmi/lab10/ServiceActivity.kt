package com.aziqazmi.lab10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aziqazmi.lab10.databinding.ActivityServiceBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Firebase.firestore

        readFirestoreData()
    }

    fun readFirestoreData(){
        db.collection("customer")
            .get()
            .addOnCompleteListener {
                val result:StringBuffer = StringBuffer()

                if(it.isSuccessful) {
                    for (document in it.result!!) {
                        val city = document.getString("city") ?: "N/A"
                        val country = document.getString("country") ?: "N/A"
                        val name = document.getString("name") ?: "N/A"
                        val phone = document.getString("phone") ?: "N/A"

                        result.append("Name: ").append(name).append("\n")
                        result.append("Phone: ").append(phone).append("\n")
                        result.append("City: ").append(city).append("\n")
                        result.append("Country: ").append(country).append("\n")

                    }
                    binding.resultTextView.text = result.toString().trim()
                }
                else
                {

                }
            }
    }
}

