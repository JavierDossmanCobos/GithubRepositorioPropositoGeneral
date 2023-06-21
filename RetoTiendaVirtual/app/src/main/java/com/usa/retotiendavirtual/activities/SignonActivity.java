package com.usa.retotiendavirtual.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.retotiendavirtual.R;

import java.util.concurrent.atomic.AtomicReference;

public class SignonActivity extends AppCompatActivity {

    EditText edEmailLogin, edPasswordLogin;
    TextView txtForgotPasswordLogin;
    Button btnInicioLogin, btnRegistroLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signon);

        btnInicioLogin=(Button)findViewById(R.id.btnInicioLogin);
        btnInicioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"InicioLogin was clicked",Toast.LENGTH_SHORT).show();
            }
        });

        

    }
}