package com.usa.retotiendavirtual.ui.producto.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.adapter.ProductoAdapter;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.LinkedList;
import java.util.List;


public class ProductoFragment extends Fragment {

    private DatabaseReference mDatabase;

    ImageSlider imgSlider;
    RecyclerView recyclerViewProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producto, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        imgSlider = view.findViewById(R.id.imgSlider);
        recyclerViewProducto = view.findViewById(R.id.recyclerViewProductos);

        //Slider image products
        List<SlideModel> imageSliders = new LinkedList<>();
        imageSliders.add(new SlideModel("https://promociondescuentos.com/wp-content/uploads/2016/02/Suburbia-zapatos.jpg", "internet", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_00, "banner08", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_01, "banner01", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_02, "banner02", ScaleTypes.FIT));
        imageSliders.add(new SlideModel("https://promociondescuentos.com/wp-content/uploads/2016/02/Suburbia-zapatos.jpg", "internet", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_03, "banner03", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_04, "banner04", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_05, "banner05", ScaleTypes.FIT));
        imageSliders.add(new SlideModel("https://promociondescuentos.com/wp-content/uploads/2016/02/Suburbia-zapatos.jpg", "internet", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_06, "banner06", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_07, "banner07", ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_08, "banner00", ScaleTypes.FIT));

        imgSlider.setImageList(imageSliders);

        imgSlider.setTouchListener(new TouchListener() {
            @Override
            public void onTouched(@NonNull ActionTypes actionTypes, int i) {
                Toast.makeText(getContext(),"Clicked on image slider >> "+String.valueOf(i),Toast.LENGTH_SHORT).show();
            }
        });

        //Recycler view products
        mostrarProductos();

        return view;
    }

    private void mostrarProductos() {
        System.out.println("childenProductos");

        DatabaseReference productosDB = mDatabase.child("productos");
        productosDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ProductoModel> firebaseproductos = new LinkedList<>();
                for (DataSnapshot childrenProductos: dataSnapshot.getChildren()) {
                    System.out.println(childrenProductos.getValue());
                    ProductoModel producto = childrenProductos.getValue(ProductoModel.class);
                    producto.setId(childrenProductos.getKey());
                    System.out.println("getKey()= "+childrenProductos.getKey());
                    System.out.println("getUrlImg()= "+producto.getUrlImg());

                    producto.setUrlImg(producto.getUrlImg());

                    firebaseproductos.add(producto);

                }
                ProductoAdapter adapter = new ProductoAdapter(firebaseproductos);
                recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewProducto.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        //return firebaseproductos;

    }
}