package com.usa.retotiendavirtual.ui.producto.fragmentos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;


public class AgregarProductoFragment extends Fragment {
    private DatabaseReference mDatabase;
    private String urlCapturedImage = null;
    FirebaseStorage cloudstorage;
    private final int CODE_INTENT_CAMERA = 10;
    private final int CODE_PERMISSION_CAMERA = 11;
    private final int CODE_INTENT_GALERY = 12;

    EditText edNombreAddProducto, edPrecioAddProducto;
    MultiAutoCompleteTextView edDescripcionAddProducto;
    ImageView imgAddProducto;
    Button btnAddProducto;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        cloudstorage = FirebaseStorage.getInstance("gs://retotiendavirtual.appspot.com");


        edNombreAddProducto = (EditText) view.findViewById(R.id.edNombreAddProducto);
        edPrecioAddProducto = (EditText) view.findViewById(R.id.edPrecioAddProducto);
        edDescripcionAddProducto = (MultiAutoCompleteTextView) view.findViewById(R.id.edDescripcionAddProducto);
        imgAddProducto = (ImageView) view.findViewById(R.id.imgAddProducto);

        btnAddProducto = (Button) view.findViewById(R.id.btnAddProducto);

        imgAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { obtenerImagen(); }
        });

        btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewbtn) {
                agregarProducto();
            }
        });
        return view;
    }

    private void obtenerImagen() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext())
                .setTitle("Obtener la Imagen del Producto")
                .setMessage("Seleccione el medio: ¿ Galeria o Camara ?")
                .setPositiveButton("camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED){
                            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraintent, CODE_INTENT_CAMERA);
                        } else {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},CODE_PERMISSION_CAMERA);
                        }

                    }
                })
                .setNegativeButton("galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            Intent galeryintent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            startActivityForResult(galeryintent, CODE_INTENT_GALERY);
                        } else {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},CODE_PERMISSION_CAMERA);
                        }

                    }
                });
        alerta.show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CODE_INTENT_CAMERA:
                // Firebase Storage
                assert data != null;
                Bitmap image = (Bitmap)data.getExtras().get("data");
                imgAddProducto.setImageBitmap(image);
                break;
            case CODE_INTENT_GALERY:
                assert data != null;
                Uri uriImagen = data.getData();
                imgAddProducto.setImageURI(uriImagen);
                StorageReference storageRef = cloudstorage.getReference();
                StorageReference riversRef = storageRef.child("productos/"+uriImagen.getLastPathSegment());
                UploadTask uploadTask = riversRef.putFile(uriImagen);

// Register observers to listen for when the download is done or if it fails
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(),"Error al subir (upload) imagen",Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriStorage = taskSnapshot.getStorage().getDownloadUrl();
                        uriStorage.addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                urlCapturedImage = o.toString();
                                System.out.println(o.toString());
                                //TODO modificar la url de la imagen
                            }
                        });


                    }
                });

                break;

        }
    }

    private void agregarProducto() {
        if(verificarCampos()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setTitle("Confirmar")
                    .setMessage("¿Esta seguro que quiere agregar el producto: "+edNombreAddProducto.getText().toString()+" ?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String nombre = edNombreAddProducto.getText().toString();
                            long precio = Long.parseLong(edPrecioAddProducto.getText().toString());
                            String descripcion = edDescripcionAddProducto.getText().toString();
                            ProductoModel producto = new ProductoModel(nombre, descripcion, precio, urlCapturedImage);
                            mDatabase.child(getString(R.string.db_name_productos)).push().setValue(producto);
                            resetCampos();
                        }
                    })
                    .setNegativeButton("No", null);
            alerta.show();
        }
    }

    private void resetCampos() {
        urlCapturedImage = null;
        imgAddProducto.setImageResource(R.drawable.baseline_photo_camera_24);
        edNombreAddProducto.setText(null);
        edDescripcionAddProducto.setText(null);
        edPrecioAddProducto.setText(null);
    }

    private boolean verificarCampos(){

        if (!edNombreAddProducto.getText().toString().isEmpty()) {
            if (!edPrecioAddProducto.getText().toString().isEmpty()) {
                if (!edDescripcionAddProducto.getText().toString().isEmpty()) {
                    if (urlCapturedImage != null) {
                        return true;
                    } else {
                        Toast.makeText(getContext(),"Capturar una imagen es requerido",Toast.LENGTH_LONG).show();
                    }
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
