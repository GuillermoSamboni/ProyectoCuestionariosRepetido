package com.cuestionarios.cuestionarioproyecto.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cuestionarios.cuestionarioproyecto.R;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

    }
    private void iniciarSesion(){

    }

//    private fun iiciarSesion() {
//        title = "Auth"
//        val email = binding.eEmailAddress.text.toString()
//        val pass = binding.ePassword.text.toString()
//
//        if (email.isNotEmpty() && pass.isNotEmpty()) {
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
//                    .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    showHome(task.result?.user?.email ?: "", ProviderType.BASIC)
//                } else {
//                    Toast.makeText(this, "Se ha producido un erro ", Toast.LENGTH_SHORT).show()
//                }
//            }
//        } else {
//            Toast.makeText(this, "llene los campos", Toast.LENGTH_SHORT).show()
//        }
//    }

}