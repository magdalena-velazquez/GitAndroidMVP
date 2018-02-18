package mx.unam.aragon.fes.diplo.gitandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button miBoton;
    private Button miBoton2;
    private Button miBoton3;
    private TextView txtVista;
    private Bitmap imagenDescargada;
    private ImageView miImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = findViewById(R.id.button);
        miBoton2 = findViewById(R.id.button2);
        miBoton3 = findViewById(R.id.button3);
        miBoton3.setText("Imagen");
        miImagen = findViewById(R.id.imageView);
        txtVista = findViewById(R.id.textView);

        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtVista.setText("Hola GitHub");
                Toast.makeText(MainActivity.this, "Se cambio el mensaje en el textView", Toast.LENGTH_SHORT).show();
            }
        });

        miBoton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtVista.setText("Diplomado Android");
            }
        });

        miBoton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new DescargarImagen().execute(new URL("http://toontown.wikia.com/wiki/Toons/Gallery?file=Chappy.png"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    class DescargarImagen extends AsyncTask<URL, Integer, Bitmap> {
            @Override
            protected Bitmap doInBackground(URL... urls) {
                try {
                    imagenDescargada = BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());
                    } catch (Exception e) {
                    Log.e("diplo", "Error" + e.toString());
                }
                return imagenDescargada;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                miImagen.setImageBitmap(imagenDescargada);
                super.onPostExecute(bitmap);
            }

    }
}






