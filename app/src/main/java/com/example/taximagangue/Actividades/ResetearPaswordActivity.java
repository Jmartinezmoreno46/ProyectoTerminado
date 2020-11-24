package com.example.taximagangue.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taximagangue.R;
import com.example.taximagangue.includes.MyToolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import dmax.dialog.SpotsDialog;


public class ResetearPaswordActivity extends AppCompatActivity {

    private TextInputEditText  editTextEmail;
    private Button btnRecuperarPasword;
    private FirebaseAuth mAuth;
    private AlertDialog tDialog;
    private  String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetear_pasword);
        tDialog = new  SpotsDialog.Builder().setContext(ResetearPaswordActivity.this).setMessage("Espere Un Momento").build();
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        btnRecuperarPasword = findViewById(R.id.btnResetearPasword);
        MyToolbar.show(this, "Recuperar Contraseña", true);

        btnRecuperarPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetarPasword();
            }
        });
    }

    private void resetarPasword() {
        email = editTextEmail.getText().toString();
        if (!email.isEmpty()){
            tDialog.show();
            mAuth.setLanguageCode("es");
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ResetearPaswordActivity.this, "Se Ha Enviado un Correo Para Restablecer Tu Contraseña", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(ResetearPaswordActivity.this, "No Se Pudo Enviar el Correo De Reestablecer Contraseña", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            tDialog.dismiss();
        }else{
            Toast.makeText(ResetearPaswordActivity.this, "Debe Ingresar Su Correo", Toast.LENGTH_SHORT).show();
        }





    }
}