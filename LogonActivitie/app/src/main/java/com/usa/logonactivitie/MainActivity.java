package com.usa.logonactivitie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnInicioLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicioLogin=(Button)findViewById(R.id.btnInicioLogin);
        btnInicioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
                SiguienteActividad();
            }
        });

    }

    private void SiguienteActividad() {
        Intent intent = new Intent(MainActivity.this, SignonActivity.class);

        startActivity(intent);

    }


}
