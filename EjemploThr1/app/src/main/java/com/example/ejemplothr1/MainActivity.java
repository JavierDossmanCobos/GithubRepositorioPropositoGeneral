package com.example.ejemplothr1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //Variables que mapean elementos del layout
    TextView text1;
    ImageView img1;
    Button boton,boton_simula;
    //Variable para diálogo de progreso
    ProgressDialog dialogoprogreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Enlazar variables con los elementos visuales
        text1 = (TextView) findViewById(R.id.txtAccion);
        img1 = (ImageView) findViewById(R.id.imagen1);
        boton = (Button) findViewById(R.id.btnCargar);
        boton_simula = (Button) findViewById(R.id.btnSimilarCarga);
        //Eventos de los botones
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargaImagen();
            }
        });
        boton_simula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarDialogo();
            }
        });
    }

    /*
        Carga de imagen desde internet
     */
    public void cargaImagen(){
        new Thread(new Runnable(){

            //Código del hilo
            private Bitmap loadImageFromNetwork(String url){
                try {
                    Bitmap imagenbmp = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                    return imagenbmp;
                } catch (IOException err) {
                    err.printStackTrace();
                }
                return null;
            }
            @Override
            public void run() {
                String url = "http://www.imagenes.com/ejemplo.jpg";
                final Bitmap miimagen = loadImageFromNetwork(url);
                //Afecto la variable del imageView
                img1.post(new Runnable() {
                    @Override
                    public void run() {
                        img1.setImageBitmap(miimagen); //Imagen de la vista le asigno la imagen descargada
                    }
                });
            }
        }).start();   //Llamar un método para crear el hilo
    }
    /*
        Simular el progreso de carga de imagen
     */
    public void lanzarDialogo(){
        dialogoprogreso = new ProgressDialog(MainActivity.this); //crea un diálogo
        //configurar el diálogo - título, mensaje, tipo, inicio, máximo
        dialogoprogreso.setTitle("Simulando descarga ");
        dialogoprogreso.setMessage("Descargando imagen ... ");
        dialogoprogreso.setProgressStyle(dialogoprogreso.STYLE_HORIZONTAL);
        dialogoprogreso.setProgress(0);
        dialogoprogreso.setMax(100);
        //mostrar diálogo
        dialogoprogreso.show();
        //código del hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                //simulo la descarga en 20 pasos
                for(int i=1;i<=20;i++){
                    //Temporizador del hilo
                    try {
                        Thread.sleep(1000); //un segundo de tiempo
                        dialogoprogreso.incrementProgressBy(100/20);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
                dialogoprogreso.dismiss(); //finaliza mostrar el diálogo de progreso
            }
        }).start();
    }
}