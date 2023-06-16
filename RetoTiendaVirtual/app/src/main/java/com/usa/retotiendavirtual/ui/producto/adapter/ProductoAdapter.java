package com.usa.retotiendavirtual.ui.producto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoViewHolder holder, int position) {
        holder.txtTituloProducto.setText(productos.get(position).getNombre());
        holder.txtDescripcionProducto.setText(productos.get(position).getDescripcion());
        holder.txtPrecioProducto.setText("$ "+String.valueOf(productos.get(position).getPrecio()));
        holder.imgProducto.setImageResource(productos.get(position).getImagen());
        holder.posicion = holder.getAdapterPosition();
    }
    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        int posicion;
        ImageView imgProducto;
        TextView txtTituloProducto, txtDescripcionProducto, txtPrecioProducto;
        public ProductoViewHolder(@NonNull View itemView) {

            super(itemView);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            txtTituloProducto = itemView.findViewById(R.id.txtTituloProducto);
            txtDescripcionProducto = itemView.findViewById(R.id.txtDescripcionProducto);
            txtPrecioProducto = itemView.findViewById(R.id.txtPrecioProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //    Toast.makeText(itemView.getContext(), "Click en un card view  "+String.valueOf(posicion),Toast.LENGTH_SHORT).show();

                    Context contexto = itemView.getContext();
                    Intent intent = new Intent(itemView.getContext(), DetallesProductoActivity.class);
                    //Todo Aqui debemos mandar los datos a la otra actividad
                    contexto.startActivity(intent);

                }
            });

        }
    }

}
