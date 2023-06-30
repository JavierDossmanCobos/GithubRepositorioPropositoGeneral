package com.usa.retotiendavirtual.ui.producto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.usa.retotiendavirtual.R;

public class DetallesProductoActivity extends AppCompatActivity {

    ImageView imgDetalleProducto;
    TextView txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    Button btnResCantidad, btnSumCantidad, btnAgregarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        imgDetalleProducto = (ImageView) findViewById((R.id.imgDetalleProducto));
        txtNombre = (TextView) findViewById((R.id.txtNombreDetalleProducto));
        txtDescripcion = (TextView) findViewById((R.id.txtDescripcionDetalleProducto));
        txtPrecio = (TextView) findViewById((R.id.txtPrecioDetalleProducto));
        txtCantidad = (TextView) findViewById((R.id.textCantidad));
        btnResCantidad = (Button) findViewById((R.id.btnResCantidad));
        btnSumCantidad = (Button) findViewById((R.id.btnSumCantidad));
        btnAgregarProducto = (Button) findViewById((R.id.btnAddProducto));

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