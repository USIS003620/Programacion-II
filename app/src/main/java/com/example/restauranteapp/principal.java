package com.example.restauranteapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;


public class principal extends Activity {
    String captar;
    EditText verga;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        ImageButton lapiz=(ImageButton)findViewById(R.id.notas);
        lapiz.setVisibility(android.view.View.INVISIBLE);
        if(Global.log.equals("1")){
            lapiz.setVisibility(android.view.View.VISIBLE);
        }

        findViewById(R.id.m1).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Global.ivar1 = "1";
                Intent i = new Intent(principal.this, categorias.class);
                // i.putExtra("elcoso", captar);
                startActivity(i);
            }
        });
        findViewById(R.id.m2).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Global.ivar1 = "2";
                Intent i = new Intent(principal.this, categorias.class);
                startActivity(i);
            }
        });
        findViewById(R.id.m3).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "3";
            }
        });
        findViewById(R.id.m4).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "4";
            }
        });
        findViewById(R.id.m5).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "5";
            }
        });

        findViewById(R.id.home).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, administrador.class));
            }
        });

        findViewById(R.id.mensaje).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, ChatGrupalActivity .class));
            }
        });

        findViewById(R.id.notas).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                principal.this.startActivity(new Intent(principal.this, Notas.class));
            }
        });


    }
}
