package com.usa.retotiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallesProductoActivity extends AppCompatActivity {

    ImageView imgDetalleProducto;
    TextView txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    Button btnResCantidad, btnSumCantidad, btnAgregarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        imgDetalleProducto = findViewById((R.id.imgDetalleProducto));
        txtNombre = findViewById((R.id.txtNombreDetalleProducto));
        txtDescripcion = findViewById((R.id.txtDescripcionDetalleProducto));
        txtPrecio = findViewById((R.id.txtPrecioDetalleProducto));
        txtCantidad = findViewById((R.id.textCantidad));
        btnResCantidad = findViewById((R.id.btnResCantidad));
        btnSumCantidad = findViewById((R.id.btnSumCantidad));
        btnAgregarProducto = findViewById((R.id.btnAgregarProducto));

        //cargarDatos();


    }
}