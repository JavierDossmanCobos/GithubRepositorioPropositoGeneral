package com.usa.retotiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        cargarDatos();
        btnResCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartCantidad();
            }
        });
        btnSumCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarCantidad();
            }
        });
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto();
            }
        });

    }

    int cantidad;
    private void cargarDatos() {
        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("nombre");
        String descripcion = datos.getString("descripcion");
        long precio = datos.getLong("precio");
        int imagen = datos.getInt("imagen");

        imgDetalleProducto.setImageResource(imagen);
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(String.valueOf(precio));
    }
    private void agregarProducto() {
    }

    private void sumarCantidad() {
        cantidad = Integer.parseInt(txtCantidad.getText().toString()) + 1;
        txtCantidad.setText(String.valueOf(cantidad));
    }

    private void restartCantidad() {
        cantidad = Integer.parseInt(txtCantidad.getText().toString());
        if (cantidad>0){
            cantidad--;
            txtCantidad.setText(String.valueOf(cantidad));
        }
    }


}