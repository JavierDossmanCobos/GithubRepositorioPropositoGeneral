package com.usa.retotiendavirtual.ui.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.adapter.ProductoAdapter;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.LinkedList;
import java.util.List;


public class ProductoFragment extends Fragment {
    RecyclerView recyclerViewProducto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producto, container, false);
        recyclerViewProducto = view.findViewById(R.id.recyclerViewProductos);

        List<ProductoModel> productos = new LinkedList<>();
        productos.add(new ProductoModel("Adidas","zapato de uso diario Adidas",250000,R.drawable.zapato_01));
        productos.add(new ProductoModel("Nike","zapato de uso diario Nike",260000,R.drawable.zapato_02));
        productos.add(new ProductoModel("Bostonian","zapato de uso diario Bostonian",270000,R.drawable.zapato_03));
        productos.add(new ProductoModel("Puma","zapato de uso diario",1250000,R.drawable.zapato_04));
        productos.add(new ProductoModel("Johnston & Murphy","zapato de uso diario Puma",450000,R.drawable.zapato_05));
        productos.add(new ProductoModel("Reebok","zapato de uso diario Reebok",650000,R.drawable.zapato_06));
        productos.add(new ProductoModel("Berluti","zapato de uso diario Berluty",750000,R.drawable.zapato_07));
        productos.add(new ProductoModel("Converse","zapato de uso diario Converse",660000,R.drawable.zapato_08));
        productos.add(new ProductoModel("Testoni","zapato de uso diario Testoni",450000,R.drawable.zapato_09));
        productos.add(new ProductoModel("Jordan","zapato de uso diario Jordan",120000,R.drawable.zapato_10));
        productos.add(new ProductoModel("King Pieces","zapato de uso diario King Pieces",250000,R.drawable.zapato_11));
        productos.add(new ProductoModel("Flabelus","zapato de uso diario Flabelus",50000,R.drawable.zapato_12));
        productos.add(new ProductoModel("Hoff","zapato de uso diario Hoff",1250000,R.drawable.zapato_13));
        productos.add(new ProductoModel("Magrit","zapato de uso diario Magrit",2250000,R.drawable.zapato_14));
        productos.add(new ProductoModel("miMaO","zapato de uso diario miMaO",550000,R.drawable.zapato_15));
        productos.add(new ProductoModel("Alohas","zapato de uso diario Alohas",900000,R.drawable.zapato_16));
        productos.add(new ProductoModel("Mascaró","zapato de uso diario Vidorreta",120000,R.drawable.zapato_17));
        productos.add(new ProductoModel("Vidorreta","zapato de uso diario Hispanitas",850000,R.drawable.zapato_18));
        productos.add(new ProductoModel("Hispanitas","zapato de uso diario Bryan Stepwise",330000,R.drawable.zapato_19));
        productos.add(new ProductoModel("Bryan Stepwise","zapato de uso diario y confortable",290000,R.drawable.zapato_20));
        productos.add(new ProductoModel("Gioseppo","zapato de uso diario Gioseppo",990000,R.drawable.zapato_21));
        productos.add(new ProductoModel("Vienty","zapato de uso diario Vienty",1230000,R.drawable.zapato_22));
        productos.add(new ProductoModel("Martinelli","zapato de uso diario Martinelli",600000,R.drawable.zapato_23));
        productos.add(new ProductoModel("Mint&Rose","zapato de uso diario Mint&Rose",250000,R.drawable.zapato_24));

        ProductoAdapter adapter = new ProductoAdapter(productos);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProducto.setAdapter(adapter);

        return view;
    }
}