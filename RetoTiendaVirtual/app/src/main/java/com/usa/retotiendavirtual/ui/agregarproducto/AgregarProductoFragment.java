package com.usa.retotiendavirtual.ui.agregarproducto;

import android.Manifest;
import android.annotation.SuppressLint;
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

import com.usa.retotiendavirtual.R;


public class AgregarProductoFragment extends Fragment {

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

        edNombreAddProducto = (EditText) view.findViewById(R.id.edNombreAddProducto);
        edPrecioAddProducto = (EditText) view.findViewById(R.id.edPrecioAddProducto);
        edDescripcionAddProducto = (MultiAutoCompleteTextView) view.findViewById(R.id.edDescripcionAddProducto);
        imgAgregarProducto = view.findViewById(R.id.imgAgregarProducto);
        btnAgregarProducto = (Button) view.findViewById(R.id.btnAgregarProducto);

        imgAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    }

    private void verificarCampos() {

    }
}
