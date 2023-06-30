package com.usa.retotiendavirtual.ui.producto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

public class EditarProdutoActivity extends AppCompatActivity {

    EditText edNombreEditProducto, edPrecioEditProducto;
    MultiAutoCompleteTextView edDescripcionEditProducto;
    ImageView imgEditProducto;
    Button btnEditProducto;
    private DatabaseReference mDatabase;
    String idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNombreEditProducto = (EditText) findViewById(R.id.edNombreEditProducto);
        edPrecioEditProducto = (EditText) findViewById(R.id.edPrecioEditProducto);
        edDescripcionEditProducto = (MultiAutoCompleteTextView) findViewById(R.id.edDescripcionEditProducto);
        imgEditProducto = (ImageView) findViewById(R.id.imgEditProducto);
        btnEditProducto = (Button) findViewById(R.id.btnEditProducto);

        cargarDatos();

        btnEditProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarProducto();
            }
        });

    }

    private void cargarDatos() {
        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("nombre");
        String descripcion = datos.getString("descripcion");
        long precio = datos.getLong("precio");
        int imagen = datos.getInt("imagen");
        idProducto = datos.getString("idProducto");

        imgEditProducto.setImageResource(imagen);
        edNombreEditProducto.setText(nombre);
        edDescripcionEditProducto.setText(descripcion);
        edPrecioEditProducto.setText(String.valueOf(precio));
    }

    private void EditarProducto() {
        if(verificarCampos()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Confirmar")
                    .setMessage("Esta seguro que quiere editar este producto?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String nombre = edNombreEditProducto.getText().toString();
                            long precio = Long.parseLong(edPrecioEditProducto.getText().toString());
                            String descripcion = edDescripcionEditProducto.getText().toString();
                            String urlImg = "https://img.freepik.com/premium-photo/white-female-sneakers-yellow-crumpled-muslin-cloth-background-flat-lay-top-view-merchandise-marketing-sale-shopping-concept_408798-95"+"17"+".jpg"; // se eliminara y la reeplazara por Firebase storage
                            ProductoModel producto = new ProductoModel(nombre, descripcion, precio, urlImg);

                            mDatabase.child(getString(R.string.db_name_productos)).child(idProducto).setValue(producto);
                            EditarProdutoActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null);
            alerta.show();
        }
    }

    private boolean verificarCampos(){

        if (!edNombreEditProducto.getText().toString().isEmpty()) {
            if (!edPrecioEditProducto.getText().toString().isEmpty()) {
                if (!edDescripcionEditProducto.getText().toString().isEmpty()) {
                    return true;
                } else {
                    edDescripcionEditProducto.setError("La descripcion del producto es requerido");
                }
            } else {
                edPrecioEditProducto.setError("El precio del producto es requerido");
            }
        } else {
            edNombreEditProducto.setError("El nombre del producto es requerido");
        }
        return false;
    }

}