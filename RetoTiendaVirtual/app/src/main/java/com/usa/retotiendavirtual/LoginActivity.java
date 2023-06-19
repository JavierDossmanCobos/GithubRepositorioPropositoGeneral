package com.usa.retotiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText edEmailLogin, edPasswordLogin;
    TextView txtForgotPasswordLogin;
    Button btnInicioLogin, btnRegistroLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnInicioLogin=(Button)findViewById(R.id.btnInicioLogin);
        btnRegistroLogin=(Button)findViewById(R.id.btnRegistroLogin);
        edEmailLogin=(EditText)findViewById(R.id.edEmailLogin);
        edPasswordLogin=(EditText)findViewById(R.id.edPasswordLogin);
        txtForgotPasswordLogin=(TextView)findViewById(R.id.txtForgotPasswordLogin);

        btnInicioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"InicioLogon was clicked",Toast.LENGTH_SHORT).show();
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        if(verificarCampos()) {
            if (edEmailLogin.getText().toString().equals("dossmanj@gmail.com") && edPasswordLogin.getText().toString().equals("123456789")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Contraseña y correo son requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarCampos() {
        if (!edEmailLogin.getText().toString().isEmpty()) {
            if (!edPasswordLogin.getText().toString().isEmpty()) {
                return true;
            } else {
                edPasswordLogin.setError("Contraseña es requerida");
            }
        } else {
            edEmailLogin.setError("Correo es requerido");
        }
        return false;
    }
}
