package com.usa.retotiendavirtual.ui.producto.holders;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.activities.CarritoDeComprasActivity;
import com.usa.retotiendavirtual.ui.producto.helpers.DBhelper;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.List;

public class ProductoShopCartViewHolder extends RecyclerView.ViewHolder {

    public int posicion;
    public TextView nombreProductoCarritoCompra, descripcionProductoCarritoCompra, precioProductoCarritoCompra, valorProductoCarritoCompra, cantidadProductoCarritoCompra;

    public Button btnSubstractProductoCarritoCompra, btnAddProductoCarritoCompra, btnDeleteProductoCarritoCompra;

    private int cantidad;
    private long valor;
    public ProductoShopCartViewHolder(@NonNull View itemView, List<ProductoModel> producto) {
        super(itemView);

        nombreProductoCarritoCompra = (TextView) itemView.findViewById(R.id.nombreProductoCarritoCompra);
        descripcionProductoCarritoCompra = (TextView) itemView.findViewById(R.id.descripcionProductoCarritoCompra);
        precioProductoCarritoCompra = (TextView) itemView.findViewById(R.id.precioProductoCarritoCompra);
        valorProductoCarritoCompra = (TextView) itemView.findViewById(R.id.valorProductoCarritoCompra);
        cantidadProductoCarritoCompra = (TextView) itemView.findViewById(R.id.cantidadProductoCarritoCompra);

        btnSubstractProductoCarritoCompra = (Button) itemView.findViewById(R.id.btnSubstractProductoCarritoCompra);
        btnAddProductoCarritoCompra = (Button) itemView.findViewById(R.id.btnAddProductoCarritoCompra);
        btnDeleteProductoCarritoCompra = (Button) itemView.findViewById(R.id.btnDeleteProductoCarritoCompra);

        btnDeleteProductoCarritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(itemView.getContext())
                        .setTitle("Eliminar Producto Carrito de Compras")
                        .setMessage("¿ Esta seguro de querer eliminar el producto ?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBhelper dbase = new DBhelper(itemView.getContext());
                                dbase.deleteProduct(producto.get(posicion).getIdDb());
                                producto.remove(posicion);
                                CarritoDeComprasActivity.updateData(producto);
                            }
                        })
                        .setNegativeButton("No", null);
                alerta.show();


            }
        });

        btnAddProductoCarritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad = Integer.parseInt(cantidadProductoCarritoCompra.getText().toString()) + 1;
                cantidadProductoCarritoCompra.setText(String.valueOf(cantidad));
                valor = Long.parseLong(precioProductoCarritoCompra.getText().toString()) * Long.parseLong(cantidadProductoCarritoCompra.getText().toString());
                valorProductoCarritoCompra.setText(String.valueOf(valor));
                producto.get(posicion).setCantidad(cantidad);
                producto.get(posicion).setValor(valor);
                updateShopCartProducts(producto.get(posicion).getIdDb(), producto.get(posicion).getCantidad(), producto.get(posicion).getValor());
                CarritoDeComprasActivity.updateData(producto);
            }
        });

        btnSubstractProductoCarritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad = Integer.parseInt(cantidadProductoCarritoCompra.getText().toString());
                if (cantidad>1){
                    cantidad--;
                    cantidadProductoCarritoCompra.setText(String.valueOf(cantidad));
                    valor = Long.parseLong(precioProductoCarritoCompra.getText().toString()) * Long.parseLong(cantidadProductoCarritoCompra.getText().toString());
                    valorProductoCarritoCompra.setText(String.valueOf(valor));
                    producto.get(posicion).setCantidad(cantidad);
                    producto.get(posicion).setValor(valor);
                    updateShopCartProducts(producto.get(posicion).getIdDb(), producto.get(posicion).getCantidad(), producto.get(posicion).getValor());
                    CarritoDeComprasActivity.updateData(producto);
                }

            }
        });

    }

    private void updateShopCartProducts (int id, Integer cantidad, long valor){
        DBhelper dbase = new DBhelper(itemView.getContext());
        dbase.updateProduct(id, cantidad,valor);

    }


}
