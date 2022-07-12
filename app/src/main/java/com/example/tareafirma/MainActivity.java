package com.example.tareafirma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tareafirma.entidades.DbSignaturess;

public class MainActivity extends AppCompatActivity
{
    Firma Firma;
    EditText txt_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_salvarfirma = findViewById(R.id.salvar);
        btn_salvarfirma.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Firma = findViewById(R.id.imgfirma);
                txt_descripcion = findViewById(R.id.descripcion);

                String descripcion = txt_descripcion.getText().toString();
                Firma.buildDrawingCache();
                Bitmap firma = Firma.getDrawingCache();

                if(descripcion.isEmpty())
                {
                    Toast.makeText(getApplicationContext()," Debe ingresar una Descripción", Toast.LENGTH_SHORT).show();
                } else
                {
                    DbSignaturess dbFirmas = new DbSignaturess(MainActivity.this);
                    long id = dbFirmas.insertarFirma(firma, descripcion);
                    if (id>0)
                    {
                        Toast.makeText(getApplicationContext(),"¡Se guardó!", Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        startActivity(getIntent());
                    }
                    else{Toast.makeText(getApplicationContext(),"No se guardó ", Toast.LENGTH_SHORT).show();;}
                }
            }
        });
    }

    private void limpiar()
    {
        Firma.NuevoDibujo();
        txt_descripcion.setText("");

    }



}