package com.usa.retotiendavirtual.ui.usuarios.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.activities.MainActivity;
import com.usa.retotiendavirtual.ui.usuarios.model.UsuarioModel;

import java.util.Calendar;
import java.util.TimeZone;

public class RegistroActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private final String FILENAME_SHARED_PREFERENCES = "LOGONdata_SIGNONuser";

    private final String KEY_ID_USUARIO = "ID_USUARIO";
    private final String KEY_EMAIL = "LOGONEMAIL";
    private final String KEY_PASSWORD = "LOGONPASSWORD";
    private final String KEY_ROLE = "LOGONROLE";

    EditText edNombreUsuario, edCorreoUsuario, edPasswordUsuario1, edPasswordUsuario2, edTelefonoUsuario, edFechaCumpleUsuario;

    Button btnRegistrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        edNombreUsuario = (EditText) findViewById(R.id.edNombreUsuario);
        edCorreoUsuario = (EditText) findViewById(R.id.edCorreoUsuario);
        edPasswordUsuario1 = (EditText) findViewById(R.id.edPasswordUsuario1);
        edPasswordUsuario2 = (EditText) findViewById(R.id.edPasswordUsuario2);
        edTelefonoUsuario = (EditText) findViewById(R.id.edTelefonoUsuario);
        edFechaCumpleUsuario = (EditText) findViewById(R.id.edFechaCumpleUsuario);
        btnRegistrarUsuario = (Button) findViewById(R.id.btnRegistrarUsuario);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

        edFechaCumpleUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verDatePicker();
            }
        });

    }

    private void verDatePicker() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int CurrentYear = calendar.get(Calendar.YEAR);
        int CurrentMonth = calendar.get(Calendar.MONTH);
        int CurrentDay = calendar.get(Calendar.DAY_OF_MONTH);
        Toast.makeText(RegistroActivity.this, String.valueOf(CurrentYear)+"/"+String.valueOf(CurrentMonth+1)+"/"+String.valueOf(CurrentDay),Toast.LENGTH_SHORT).show();

        DatePickerDialog dialogDate = new DatePickerDialog(RegistroActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int CurrentYear, int CurrentMonth, int CurrentDay) {
                Toast.makeText(RegistroActivity.this, "Fecha: "+String.valueOf(CurrentYear)+"/"+String.valueOf(CurrentMonth+1)+"/"+String.valueOf(CurrentDay),Toast.LENGTH_SHORT).show();
                String fechaCumpleanos = String.valueOf(CurrentYear)+"-"+String.valueOf(CurrentMonth + 1)+"-"+String.valueOf(CurrentDay);
                edFechaCumpleUsuario.setText(fechaCumpleanos);

            }
        }, CurrentYear, CurrentMonth, CurrentDay);
        dialogDate.show();
    }


    private void RegistrarUsuario() {
        if(verificarCampos()){
        if(edPasswordUsuario1.getText().equals(edPasswordUsuario1.getText())){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this)
                    .setTitle("Confirmar")
                    .setMessage("¿Esta seguro que quiere registrar este nuevo usuario?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String nombre = edNombreUsuario.getText().toString();
                            String correo = edCorreoUsuario.getText().toString();
                            String palabraclave = edPasswordUsuario1.getText().toString();
                            String telefono = edTelefonoUsuario.getText().toString();
                            String fechacumple = edFechaCumpleUsuario.getText().toString();
                            String rol = "client";

                            UsuarioModel usuario = new UsuarioModel(nombre, correo, palabraclave, fechacumple, telefono, rol);
  //                        Toast.makeText(, "usuario "+String.valueOf(nombre)+","+String.valueOf(correo)+","+String.valueOf(palabraclave)+","+String.valueOf(fechacumple)+","+String.valueOf(telefono)+","+String.valueOf(rol));
                            mDatabase.child("usuarios").push().setValue(usuario);

                            SharedPreferences sharedPreferences = getSharedPreferences(FILENAME_SHARED_PREFERENCES,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ID_USUARIO,"-NZ80oeqB7VCtnfNzLQH");
                            editor.putString(KEY_EMAIL,correo);
                            editor.putString(KEY_PASSWORD,palabraclave);
                            editor.putString(KEY_ROLE,rol);
                            editor.commit();

                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();



                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO Controlar que hacer si responde No
                        }
                    });
            dialogo.show();
        } else {
            Toast.makeText(RegistroActivity.this,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show();
        }
        } else {
            Toast.makeText(RegistroActivity.this,"verifique los campos que son requeridos",Toast.LENGTH_SHORT).show();
        }

    }
    private boolean verificarCampos(){
        if(!edNombreUsuario.getText().toString().isEmpty()){
            if(!edCorreoUsuario.getText().toString().isEmpty()){
                if(!edPasswordUsuario1.getText().toString().isEmpty()){
                    if(!edPasswordUsuario2.getText().toString().isEmpty()){
                        if(!edTelefonoUsuario.getText().toString().isEmpty()){
                            if(!edFechaCumpleUsuario.getText().toString().isEmpty()){
                                return true;
                            }else {
                                edFechaCumpleUsuario.setError("Fecha cumpleaños del usuario es requerido");
                            }
                        }else {
                            edTelefonoUsuario.setError("Telefono usuario es requerido");
                        }
                    }else {
                        edPasswordUsuario2.setError("Repetir Contraseña usuario es requerido");
                    }
                }else {
                    edPasswordUsuario1.setError("Contraseña usuario es requerido");
                }
            }else {
                edCorreoUsuario.setError("Correo usuario es requerido");
            }
        }else {
            edNombreUsuario.setError("Nombre usuario es requerido");
        }
        return false;
    }
}