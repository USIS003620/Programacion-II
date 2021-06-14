package com.example.restauranteapp;

import android.app.Activity;
import android.content.Intent;


public class categorias extends Activity {
    String cabron;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

       /* Bundle b=getIntent().getExtras();
        cabron=b.getString("elcoso");*/

        findViewById(R.id.sopas).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                categorias.this.startActivity(new Intent(categorias.this, porciones.class));
            }
        });
        findViewById(R.id.res).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                categorias.this.startActivity(new Intent(categorias.this, parrillada.class));
            }
        });


        findViewById(R.id.atras).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                categorias.this.startActivity(new Intent(categorias.this, principal.class));
            }
        });

        findViewById(R.id.bebidas).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                categorias.this.startActivity(new Intent(categorias.this, bebidas.class));
            }
        });
        findViewById(R.id.mariscos).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                categorias.this.startActivity(new Intent(categorias.this, mariscos.class));
            }
        });
    }


    public String pedido() {
       String ped=cabron;
        return ped;
    }
}