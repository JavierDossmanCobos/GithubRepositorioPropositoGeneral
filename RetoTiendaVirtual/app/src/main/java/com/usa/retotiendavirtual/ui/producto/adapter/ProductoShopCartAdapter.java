package com.usa.retotiendavirtual.ui.producto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.holders.ProductoShopCartViewHolder;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.List;

public class ProductoShopCartAdapter extends RecyclerView.Adapter<ProductoShopCartViewHolder> {

    private List<ProductoModel> productos;

    public ProductoShopCartAdapter(List<ProductoModel> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_carrito_de_compras, parent, false);
        return new ProductoShopCartViewHolder(view,productos);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoShopCartViewHolder holder, int position) {

        holder.nombreProductoCarritoCompra.setText(productos.get(position).getNombre());
        holder.descripcionProductoCarritoCompra.setText(productos.get(position).getDescripcion());
        holder.cantidadProductoCarritoCompra.setText(String.valueOf(productos.get(position).getCantidad()));
        holder.precioProductoCarritoCompra.setText(String.valueOf(productos.get(position).getPrecio()));
        holder.valorProductoCarritoCompra.setText(String.valueOf(productos.get(position).getValor()));
        holder.posicion = holder.getAdapterPosition();




    }


}
