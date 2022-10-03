package com.example.primedentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.primedentapp.databinding.ActivityInicioSesionBinding
import com.google.firebase.auth.FirebaseAuth

class InicioSesion : AppCompatActivity() {

    private lateinit var binding:ActivityInicioSesionBinding
    private lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            val intent = Intent(this,RegistroSesion::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.textInputEditText.text.toString()
            val pass = binding.passInputEditText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }


            }else{
                Toast.makeText(this,"Ingrese datos porfavor", Toast.LENGTH_SHORT).show()
            }

        }
    }
}