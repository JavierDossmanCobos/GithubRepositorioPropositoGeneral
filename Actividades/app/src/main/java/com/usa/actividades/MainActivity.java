package com.usa.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteActividad();
            }
        });
}

    private void siguienteActividad() {
        Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
        //Todo  enviar datos
        intent.putExtra("nombreProducto", "Calzado Adidas");
        intent.putExtra("precioProducto",35000000);
        startActivity(intent);

    }
    }