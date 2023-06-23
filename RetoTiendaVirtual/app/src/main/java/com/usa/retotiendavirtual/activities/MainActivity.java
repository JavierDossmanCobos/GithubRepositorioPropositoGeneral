package com.usa.retotiendavirtual.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.databinding.ActivityMainBinding;
import com.usa.retotiendavirtual.ui.usuarios.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME_SHARED_PREFERENCES = "LOGONdata_SIGNONuser";
    private final String KEY_ROLE = "LOGONROLE";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mostrarItemsPorRol(navigationView);

        navigationView.getMenu().findItem(R.id.nav_agregar_producto).setVisible(true);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_productos, R.id.nav_servicios, R.id.nav_sucursales, R.id.nav_agregar_producto)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void mostrarItemsPorRol(NavigationView navigationView) {
        //  SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES, MODE_PRIVATE);
        String logonrole = sharedPreferences.getString(KEY_ROLE, "");
        if(logonrole.equals("admin")) {
            navigationView.getMenu().findItem(R.id.nav_agregar_producto).setVisible(true);
        } else if(logonrole.equals("client")) {
            navigationView.getMenu().findItem(R.id.nav_agregar_producto).setVisible(false);
        } else {
            navigationView.getMenu().findItem(R.id.nav_agregar_producto).setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ItemCompras) {
            Toast.makeText(this, "Item Carrito de Compras", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ItemNosotros) {
            Toast.makeText(this, "Item Nosotros", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ItemCerrarSesion) {
            cerrarSesion();
           // Toast.makeText(this, "Item Cerrar Sesion", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cerrarSesion() {
        //TODO logica de cerrar sesion
        SharedPreferences sharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}