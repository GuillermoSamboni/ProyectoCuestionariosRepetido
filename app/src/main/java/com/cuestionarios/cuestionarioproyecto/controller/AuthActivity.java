package com.cuestionarios.cuestionarioproyecto.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cuestionarios.cuestionarioproyecto.HomeActivity;
import com.cuestionarios.cuestionarioproyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnIniciarSesion;
    EditText emailLogin, passLogin;
    TextView registrarme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        btnIniciarSesion = findViewById(R.id.idBtnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        registrarme = findViewById(R.id.idTxtRegistrarme);

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intentHome);
        }
    }

    private void iniciarSesion() {
        emailLogin = findViewById(R.id.idUserEmail);
        passLogin = findViewById(R.id.idUserPassword);
        if (emailLogin.getText().toString().isEmpty() && passLogin.getText().toString().isEmpty()) {
            Toast.makeText(this, "debe llenar los datos", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.signInWithEmailAndPassword(emailLogin.getText().toString(), passLogin.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(AuthActivity.this, "LoginExitoso", Toast.LENGTH_SHORT).show();

                                Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intentHome);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(AuthActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
    }


}