package com.aziqazmi.lab10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aziqazmi.lab10.databinding.ActivityNewAccountBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class NewAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.btnCreate.setOnClickListener {
            createUser(binding.signupEmailText.text.toString(), (binding.signupPasswordEditText.text.toString()))
        }

        db = Firebase.firestore
    }

    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->

            if(task.isSuccessful){
                newCustomer()
                val intent = Intent(this, ThankYouActivity::class.java)
                startActivity(intent)
            }
            else {
                Snackbar.make(
                    binding.root,
                    "Enter a valid username and password",
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun newCustomer(){
        val customer = hashMapOf(
            "name" to binding.signupNameEditText.text.toString().trim(),
            "city" to binding.signupCityEditText.text.toString().trim(),
            "country" to binding.signupCountryEditText.text.toString().trim(),
            "phone" to binding.signupPhoneEditText.text.toString().trim(),
            "email" to binding.signupEmailText.text.toString().trim(),
            "password" to binding.signupPasswordEditText.text.toString().trim()

        )

        db.collection("customer")
            .add(customer)
            .addOnSuccessListener {
                documentReference ->
                Log.d("debug", "Document successfully added with id ${documentReference.id}")

                val intent = Intent(this, ThankYouActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Log.d("debug", "An error happen ${e.message}")
            }
    }
}