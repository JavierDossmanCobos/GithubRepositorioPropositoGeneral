package com.usa.retotiendavirtual.ui.producto.fragmentos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.activities.EditarProdutoActivity;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.Random;


public class AgregarProductoFragment extends Fragment {

    private DatabaseReference mDatabase;

    private final int CODE_INTENT_CAMERA = 10;
    private final int CODE_PERMISSION_CAMERA = 11;

    EditText edNombreAddProducto, edPrecioAddProducto;
    MultiAutoCompleteTextView edDescripcionAddProducto;
    ImageView imgAgregarProducto;
    Button btnAgregarProducto;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNombreAddProducto = (EditText) view.findViewById(R.id.edNombreAddProducto);
        edPrecioAddProducto = (EditText) view.findViewById(R.id.edPrecioAddProducto);
        edDescripcionAddProducto = (MultiAutoCompleteTextView) view.findViewById(R.id.edDescripcionAddProducto);
        imgAgregarProducto = view.findViewById(R.id.imgAgregarProducto);
        btnAgregarProducto = (Button) view.findViewById(R.id.btnAgregarProducto);

        imgAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewimg) {
                tomarFoto();
            }
        });

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewbtn) {
                agregarProducto();
            }
        });
        return view;
    }

    private void tomarFoto() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED){
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent, CODE_INTENT_CAMERA);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},CODE_PERMISSION_CAMERA);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CODE_INTENT_CAMERA:
                Bitmap image = (Bitmap)data.getExtras().get("data");
                imgAgregarProducto.setImageBitmap(image);
                break;
        }
    }

    private void agregarProducto() {
        if(verificarCampos()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setTitle("Confirmar")
                    .setMessage("Esta seguro que quiere agregar este producto?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String nombre = edNombreAddProducto.getText().toString();
                            long precio = Long.parseLong(edPrecioAddProducto.getText().toString());
                            String descripcion = edDescripcionAddProducto.getText().toString();
                            String urlImg = "https://img.freepik.com/premium-photo/white-female-sneakers-yellow-crumpled-muslin-cloth-background-flat-lay-top-view-merchandise-marketing-sale-shopping-concept_408798-95"+"17"+".jpg"; // se eliminara y la reeplazara por Firebase storage
                            ProductoModel producto = new ProductoModel(nombre, descripcion, precio, urlImg);

                            mDatabase.child(getString(R.string.db_name_productos)).push().setValue(producto);

                        }
                    })
                    .setNegativeButton("No", null);
            alerta.show();
        }
    }

    private boolean verificarCampos(){

        if (!edNombreAddProducto.getText().toString().isEmpty()) {
            if (!edPrecioAddProducto.getText().toString().isEmpty()) {
                if (!edDescripcionAddProducto.getText().toString().isEmpty()) {
                    return true;
                } else {
                    edDescripcionAddProducto.setError("La descripcion del producto es requerido");
                }
            } else {
                edPrecioAddProducto.setError("El precio del producto es requerido");
            }
        } else {
            edNombreAddProducto.setError("El nombre del producto es requerido");
        }
        return false;
    }

}
