package com.usa.retotiendavirtual.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.retotiendavirtual.R;


public class LoginActivity extends AppCompatActivity {

    private final String NOMBRE_SHARED = "DATOS_USUARIO";
    private final String KEY_CORREO = "DATOS_USUARIO";
    private final String nombreArchivoSharedPreferences = "DATOS_USUARIO";

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

        btnRegistroLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(),"InicioLogon was clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarSesionDatosGuardados(){
      //  SharedPreferences.

    }
    private void iniciarSesion() {
        String theemail = edEmailLogin.getText().toString();
        String thepassword = edPasswordLogin.getText().toString();
        String therole = "admin";
        if(verificarCampos()) {
            if (edEmailLogin.getText().toString().equals("dossmanj@gmail.com") && edPasswordLogin.getText().toString().equals("123456789")) {
                //TODO almacena info en un archivo privado
                SharedPreferences sharedPreferences = getSharedPreferences(nombreArchivoSharedPreferences,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("CORREO",theemail);
                editor.putString("PASSWORD",thepassword);
                editor.putString("ROL",therole);
                editor.commit();

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
