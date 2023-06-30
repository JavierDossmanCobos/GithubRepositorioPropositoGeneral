package com.usa.retotiendavirtual.ui.producto.holders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.producto.activities.DetallesProductoActivity;
import com.usa.retotiendavirtual.ui.producto.activities.EditarProdutoActivity;
import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.List;

public class ProductoViewHolder extends RecyclerView.ViewHolder {

    private DatabaseReference mDatabase;
    private final String FILENAME_SHARED_PREFERENCES = "LOGONdata_SIGNONuser";
    private final String KEY_ROLE = "LOGONROLE";
    public ImageView imgProducto;
    public TextView txtTituloProducto, txtDescripcionProducto, txtPrecioProducto;
    public ImageButton btnEliminaProducto, btnEditaProducto;
    public LinearLayout opcionesAdminProducto;
    public int position;

    public ProductoViewHolder(@NonNull View itemView, List<ProductoModel> productos) {
        super(itemView);

        imgProducto = (ImageView) itemView.findViewById(R.id.imgCardViewSucursal);
        txtTituloProducto = (TextView) itemView.findViewById(R.id.txtTituloProducto);
        txtDescripcionProducto = (TextView) itemView.findViewById(R.id.txtDescripcionProducto);
        txtPrecioProducto = (TextView) itemView.findViewById(R.id.txtPrecioProducto);
        btnEliminaProducto = (ImageButton) itemView.findViewById(R.id.btnEliminaProducto);
        btnEditaProducto = (ImageButton) itemView.findViewById(R.id.btnEditaProducto);
        opcionesAdminProducto = (LinearLayout) itemView.findViewById(R.id.opcionesAdminProducto);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Context context = itemView.getContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME_SHARED_PREFERENCES, context.MODE_PRIVATE);
        String logonrole = sharedPreferences.getString(KEY_ROLE, "admin");

        if(logonrole.equals("admin")) {
            opcionesAdminProducto.setVisibility(View.VISIBLE);
        } else {
            opcionesAdminProducto.setVisibility(View.INVISIBLE);
        }

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

        btnEliminaProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(context.getString(R.string.db_name_productos)).child(productos.get(position).getId()).removeValue();
            }
        });

        btnEditaProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentedit = new Intent(context, EditarProdutoActivity.class);
                intentedit.putExtra("nombre",productos.get(position).getNombre());
                intentedit.putExtra("descripcion",productos.get(position).getDescripcion());
                intentedit.putExtra("precio",productos.get(position).getPrecio());
                intentedit.putExtra("imagen", productos.get(position).getImagen());
                intentedit.putExtra("idProducto", productos.get(position).getId());
                context.startActivity(intentedit);
            }
        });
    }

}
