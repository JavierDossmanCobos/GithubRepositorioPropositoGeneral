package com.usa.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    TextView txtNombreProducto, txtPrecioProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        txtNombreProducto = findViewById(R.id.txtNombreProducto);
        txtPrecioProducto = findViewById(R.id.txtPrecioProducto);
        Bundle datos = getIntent().getExtras();

        String nombre = datos.getString("nombreProducto");
        int precio = datos.getInt("precioProducto");

        txtNombreProducto.setText(nombre);
        txtPrecioProducto.setText("$"+String.valueOf(precio));

    }

}