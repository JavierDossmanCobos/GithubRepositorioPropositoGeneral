package com.usa.retotiendavirtual.ui.producto.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.adapter.ProductoShopCartAdapter;
import com.usa.retotiendavirtual.ui.producto.helpers.DBhelper;
import com.usa.retotiendavirtual.ui.producto.model.CarritoDeCompraModel;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CarritoDeComprasActivity extends AppCompatActivity {

    private static RecyclerView recyclerViewCarritoDeCompras;

    private static TextView txtTotalItems, txtTotalValue;

    private static Button btnPerformPurchase;
    private static ProductoShopCartAdapter adapter;

    private static List<ProductoModel> productos;

    private static DBhelper dbase;

    private DatabaseReference mDatabase;

    private static long totalvalue;
    private static int totalitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_de_compras);
        txtTotalItems = (TextView) findViewById(R.id.txtTotalItems);
        txtTotalValue = (TextView) findViewById(R.id.txtTotalValue);
        btnPerformPurchase = (Button) findViewById(R.id.btnPerformPurchase);

        recyclerViewCarritoDeCompras = findViewById((R.id.recyclerViewCarritoDeCompras));
        dbase = new DBhelper(this);
        recyclerViewCarritoDeCompras.setLayoutManager(new LinearLayoutManager(this));
        productos = dbase.getallProducts();
        updateData(productos);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnPerformPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alet = new AlertDialog.Builder(CarritoDeComprasActivity.this)
                        .setTitle("Realizar la compra")
                        .setMessage("¿Esta seguro de realizar esta compra?")
                        .setNegativeButton("No",null)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                List<ProductoModel> shoppingCartProducts = new LinkedList<>();
                                String idUsuario = "-NZ80oeqB7VCtnfNzLQH";
                                String idSucursal = null;
                                String fecha = new Date().toString();
                                for (ProductoModel producto : productos) {
                                    shoppingCartProducts.add(new ProductoModel(producto.getId(), producto.getPrecio(), producto.getCantidad(), producto.getValor()));
                                }
                                CarritoDeCompraModel carritoDeCompra = new CarritoDeCompraModel(idUsuario, idSucursal, fecha, totalvalue, totalitems, shoppingCartProducts);
                                mDatabase.child("compras").push().setValue(carritoDeCompra);
                                finish();
                            }
                        });
                alet.show();

            }
        });

    }

    public static void updateData(List<ProductoModel> productosList){
        totalvalue = 0;
        totalitems = 0;

        for (ProductoModel producto : productosList) {
            totalvalue += producto.getCantidad() * producto.getPrecio();
            totalitems += 1;
        }
        if (totalitems > 0) {
            txtTotalValue.setText("Valor Total: $ "+totalvalue);
            txtTotalItems.setText(" Items: "+totalitems);
        } else {
            txtTotalValue.setText("vacio");
            txtTotalItems.setText("vacio");
            btnPerformPurchase.setVisibility(View.INVISIBLE);
        }

        adapter = new ProductoShopCartAdapter(productosList);
        recyclerViewCarritoDeCompras.setAdapter(adapter);

    }

}
