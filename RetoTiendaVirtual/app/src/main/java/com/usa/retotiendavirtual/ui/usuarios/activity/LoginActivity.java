package com.usa.retotiendavirtual.ui.usuarios.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.activities.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private final String FILENAME_SHARED_PREFERENCES = "LOGONdata_SIGNONuser";
    private final String KEY_EMAIL = "LOGONEMAIL";
    private final String KEY_PASSWORD = "LOGONPASSWORD";
    private final String KEY_ROLE = "LOGONROLE";

    EditText edEmailLogin, edPasswordLogin;
    TextView txtForgotPasswordLogin;
    Button btnInicioLogin, btnRegistroLogin;

    private FirebaseAuth mAuth;

    // Initialize Firebase Auth
    //mAuth = FirebaseAuth.getInstance();

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

    @Override
    protected void onResume() {
        super.onResume();
        iniciarSesionDatosGuardados();
    }

    private void iniciarSesionDatosGuardados() {
        //  SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES, MODE_PRIVATE);
        String logonemail = sharedPreferences.getString(KEY_EMAIL, null);
        String logonpassword = sharedPreferences.getString(KEY_PASSWORD, null);
        String logonrole = sharedPreferences.getString(KEY_ROLE, null);

        if (logonemail != null && logonpassword != null && logonrole != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void iniciarSesion() {

        String logonemail = edEmailLogin.getText().toString();
        String logonpassword = edPasswordLogin.getText().toString();
        String logonrole = "admin";
        if(verificarCampos()) {

            SharedPreferences sharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES, MODE_PRIVATE);
            String signonemail = sharedPreferences.getString(KEY_EMAIL, "admin");
            String signonpassword = sharedPreferences.getString(KEY_PASSWORD, "admin");

            Toast.makeText(LoginActivity.this, "Last Logon: USEREMAIL={"+String.valueOf(signonemail)+"}  PASSWORD={"+String.valueOf(signonpassword)+"}",Toast.LENGTH_SHORT).show();

            if (edEmailLogin.getText().toString().equals(signonemail) && edPasswordLogin.getText().toString().equals(signonpassword)) {
                //TODO almacena info en un archivo privado
                //
                SharedPreferences updatesharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor editor = updatesharedPreferences.edit();
                editor.putString(KEY_EMAIL,logonemail);
                editor.putString(KEY_PASSWORD,logonpassword);
                editor.putString(KEY_ROLE,logonrole);
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
