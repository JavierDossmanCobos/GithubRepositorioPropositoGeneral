package com.usa.retotiendavirtual.ui.producto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.helpers.DBhelper;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

public class DetallesProductoActivity extends AppCompatActivity {

    ImageView imgDetalleProducto;
    TextView txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    Button btnResCantidad, btnSumCantidad, btnAgregarProducto;

    String nombre;
    String descripcion;
    String idFirebase;
    int cantidad;
    long precio;
    long valor;


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

    private void cargarDatos() {
        Bundle datos = getIntent().getExtras();
        nombre = datos.getString("nombre");
        descripcion = datos.getString("descripcion");
        precio = datos.getLong("precio");
        int imagen = datos.getInt("imagen");
        idFirebase = datos.getString("idProducto");
        imgDetalleProducto.setImageResource(imagen);
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(String.valueOf(precio));
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

    private void agregarProducto() {
        String numberProducto = txtCantidad.getText().toString();
        valor = Long.parseLong(numberProducto) * precio;
        if(Integer.parseInt(numberProducto) != 0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Agregar Producto ")
                    .setMessage("¿Esta seguro que quiere agregar "+numberProducto+" a su carrito de compras?")
                       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBhelper dbase = new DBhelper(getBaseContext());
                            dbase.addProduct(nombre, descripcion, precio, Integer.parseInt(numberProducto), valor, idFirebase);
                            DetallesProductoActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null);
            alerta.show();


    }




    }



}