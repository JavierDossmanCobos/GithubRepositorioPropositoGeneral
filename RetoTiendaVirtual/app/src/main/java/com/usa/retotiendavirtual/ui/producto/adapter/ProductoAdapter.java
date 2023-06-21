package com.usa.retotiendavirtual.ui.producto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usa.retotiendavirtual.DetallesProductoActivity;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<ProductoModel> productos;

    public ProductoAdapter(List<ProductoModel> productos) {
        this.productos = productos;
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @NonNull
    @Override
    public ProductoAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto, parent, false);
        return new ProductoViewHolder(view);
    }


    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProducto;
        TextView txtTituloProducto, txtDescripcionProducto, txtPrecioProducto;

        int position;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProducto = itemView.findViewById(R.id.imgSucursal);
            txtTituloProducto = itemView.findViewById(R.id.txtTituloProducto);
            txtDescripcionProducto = itemView.findViewById(R.id.txtDescripcionProducto);
            txtPrecioProducto = itemView.findViewById(R.id.txtPrecioProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context contexto = itemView.getContext();
                    Intent intent = new Intent(contexto, DetallesProductoActivity.class);
                    intent.putExtra("nombre",productos.get(position).getNombre());
                    intent.putExtra("descripcion",productos.get(position).getDescripcion());
                    intent.putExtra("precio",productos.get(position).getPrecio());
                    intent.putExtra("imagen", productos.get(position).getImagen());
                    contexto.startActivity(intent);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoViewHolder holder, int position) {
        holder.txtTituloProducto.setText(productos.get(position).getNombre());
        holder.txtDescripcionProducto.setText(productos.get(position).getDescripcion());
        holder.txtPrecioProducto.setText("$ "+String.valueOf(productos.get(position).getPrecio()));
        holder.imgProducto.setImageResource(productos.get(position).getImagen());
        holder.position = holder.getAdapterPosition();
    }
}
