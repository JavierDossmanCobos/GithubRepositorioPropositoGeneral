package com.usa.retotiendavirtual.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.usa.retotiendavirtual.R;

import java.util.Calendar;
import java.util.TimeZone;

public class RegistroActivity extends AppCompatActivity {

    EditText edNombreUsuario, edCorreoUsuario, edPasswordUsuario1, edPasswordUsuario2, edTelefonoUsuario, edFechaCumpleUsuario;

    Button btnRegistrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNombreUsuario = (EditText)findViewById(R.id.edNombreUsuario);
        edCorreoUsuario = (EditText)findViewById(R.id.edCorreoUsuario);
        edPasswordUsuario1 = (EditText)findViewById(R.id.edPasswordUsuario1);
        edPasswordUsuario2 = (EditText)findViewById(R.id.edPasswordUsuario2);
        edTelefonoUsuario = (EditText)findViewById(R.id.edTelefonoUsuario);
        edFechaCumpleUsuario = (EditText)findViewById(R.id.edFechaCumpleUsuario);
        btnRegistrarUsuario = (Button)findViewById(R.id.btnRegistrarUsuario);

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
            DatePickerDialog dialogDate = new DatePickerDialog(RegistroActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             // Toast.makeText(RegistroActivity.this, String.valueOf(CurrentYear)+"/"+String.valueOf(CurrentMonth)+"/"+String.valueOf(CurrentDay),Toast.LENGTH_SHORT).show();
                String FechaCumle = String.valueOf(CurrentYear)+"-"+String.valueOf(CurrentMonth + 1)+"-"+String.valueOf(CurrentDay);
                edFechaCumpleUsuario.setText(FechaCumle);

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
                            //TODO aqui debe llamarse el endpoit para crear el usuario a la DB
                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
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