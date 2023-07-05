package com.usa.retotiendavirtual.ui.producto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.adapter.ProductoShopCartAdapter;
import com.usa.retotiendavirtual.ui.producto.helpers.DBhelper;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.List;

public class CarritoDeComprasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCarritoDeCompras;
    private ProductoShopCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_de_compras);
        recyclerViewCarritoDeCompras = findViewById((R.id.recyclerViewCarritoDeCompras));
        DBhelper dbase = new DBhelper(this);
        List<ProductoModel> productos = dbase.getallProducts();
        adapter = new ProductoShopCartAdapter(productos);
        recyclerViewCarritoDeCompras.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarritoDeCompras.setAdapter(adapter);

    }
}