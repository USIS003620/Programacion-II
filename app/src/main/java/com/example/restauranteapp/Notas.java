package com.example.restauranteapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


public class Notas extends Activity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        findViewById(R.id.p1).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "1";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.p2).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "2";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.p3).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "3";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.p4).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "4";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.p5).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "5";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.imageButton6).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Notas.this.startActivity(new Intent(Notas.this, principal.class));
            }
        });


    }
    public void bloq(View v){
        Global.log="0";
        Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Notas.this, principal.class);
        startActivity(i);
    }

}
