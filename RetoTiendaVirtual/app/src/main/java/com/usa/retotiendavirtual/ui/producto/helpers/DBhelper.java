package com.usa.retotiendavirtual.ui.producto.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.usa.retotiendavirtual.ui.producto.model.ProductoModel;

import java.util.LinkedList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RetoTiendaVirtual.db";
    private static final int DATABASE_VERSION = 1;
    private final String TABLE_PRODUCTS = "productos";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE 'productos' ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'descripcion' TEXT NOT NULL, 'precio' NUMERIC NOT NULL, 'cantidad' INTEGER NOT NULL, 'valor' NUMERIC NOT NULL, 'id_firebase' TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    public boolean addProduct(String nombre, String descripcion, long precio, int cantidad, long valor, String idFirebase) {
        try {
            SQLiteDatabase dbase = this.getWritableDatabase();
            ContentValues drecord = new ContentValues();
            drecord.put("nombre", nombre);
            drecord.put("descripcion", descripcion);
            drecord.put("precio", precio);
            drecord.put("cantidad", cantidad);
            drecord.put("valor", valor);
            drecord.put("id_firebase", idFirebase);
            dbase.insert(TABLE_PRODUCTS, null, drecord);
            dbase.close();
            return true;
        } catch (Exception e) {
            Log.e("AddProduct", e.toString());
            return false;
        }
    }

    public boolean updateProduct(int id, Integer cantidad, long valor) {
        try {
            SQLiteDatabase dbase = this.getWritableDatabase();
            ContentValues drecord = new ContentValues();
            drecord.put("cantidad", cantidad);
            drecord.put("valor", valor);

            dbase.update(TABLE_PRODUCTS, drecord, "id=?", new String[]{Integer.toString(id)});
            dbase.close();
            return true;
        } catch (Exception e) {
            Log.e("updateProduct", e.toString());
            return false;
        }
    }


    public boolean deleteProduct(int id) {
        try {
            SQLiteDatabase dbase = this.getWritableDatabase();
            dbase.delete(TABLE_PRODUCTS, "id=?", new String[]{Integer.toString(id)});
            dbase.close();
            return true;
        } catch (Exception e) {
            Log.e("updateProduct", e.toString());
            return false;
        }
    }

    @SuppressLint("Range")
    public List<ProductoModel> getallProducts() {
        try {
            List<ProductoModel> shoppingcartProducts = new LinkedList<>();
            SQLiteDatabase dbase = this.getWritableDatabase();
            Cursor dbresponse = dbase.rawQuery("select * from " + TABLE_PRODUCTS, null);
            dbresponse.moveToFirst();
            while (!dbresponse.isAfterLast()) {

                String nombre = dbresponse.getString(dbresponse.getColumnIndex("nombre"));
                String descripcion = dbresponse.getString(dbresponse.getColumnIndex("descripcion"));
                long precio = dbresponse.getLong(dbresponse.getColumnIndex("precio"));
                String idFirebase = dbresponse.getString(dbresponse.getColumnIndex("id_firebase"));
                int cantidad = dbresponse.getInt(dbresponse.getColumnIndex("cantidad"));
                long valor = dbresponse.getLong(dbresponse.getColumnIndex("valor"));
                int idDb = dbresponse.getInt(dbresponse.getColumnIndex("id"));

                ProductoModel shoppingcartproducto = new ProductoModel(nombre, descripcion, precio, idFirebase, cantidad, valor, idDb);
                shoppingcartProducts.add(shoppingcartproducto);
                dbresponse.moveToNext();
            }
            return shoppingcartProducts;

        } catch (Exception e) {
            Log.e("getallProducts", e.toString());
            return null;
        }
    }
}




