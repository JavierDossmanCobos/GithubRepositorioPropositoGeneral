package com.usa.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNumA,etNumB;
    Button btnSumar,btnRestar, btnMultiplicar, btnDividir;
    TextView txtRta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumA = findViewById(R.id.etNumA);
        etNumB = findViewById(R.id.etNumB);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        txtRta = findViewById(R.id.txtRta);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numA = Double.parseDouble(etNumA.getText().toString());
                double numB = Double.parseDouble(etNumB.getText().toString());
                String rta = String.valueOf(numA+numB);
                txtRta.setText("Rta: "+rta);
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numA = Double.parseDouble(etNumA.getText().toString());
                double numB = Double.parseDouble(etNumB.getText().toString());
                String rta = String.valueOf(numA-numB);
                txtRta.setText("Rta: "+rta);
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numA = Double.parseDouble(etNumA.getText().toString());
                double numB = Double.parseDouble(etNumB.getText().toString());
                String rta = String.valueOf(numA*numB);
                txtRta.setText("Rta: "+rta);
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numA = Double.parseDouble(etNumA.getText().toString());
                double numB = Double.parseDouble(etNumB.getText().toString());
                String rta = String.valueOf(numA/numB);
                txtRta.setText("Rta: "+rta);
            }
        });

    }
}