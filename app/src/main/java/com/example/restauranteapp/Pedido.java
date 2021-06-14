package com.example.restauranteapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Pedido extends Activity {
        android.widget.ListView listado;
        ImageButton clear,pedido;
        android.widget.TextView desc, pre;
        Integer[] arreglo3;
        String dato;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        FirebaseMessaging.getInstance().subscribeToTopic("enviarNotify").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });

        clear = (ImageButton) findViewById(R.id.clear);
        pedido = (ImageButton) findViewById(R.id.btnListo);
        desc = (android.widget.TextView) findViewById(R.id.item);
        pre = (android.widget.TextView) findViewById(R.id.itempre);

        android.os.Bundle p = getIntent().getExtras();
        dato = p.getString("eso");

        Listar();
        clear.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                new AlertDialog.Builder(Pedido.this)
                        .setTitle("Confirmacion de Eliminacion")
                        .setMessage("Estas seguro de eliminar\n todos los platos?")
                        .setPositiveButton("Si Completamente ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Borrar();
                                Pedido.this.startActivity(new Intent(Pedido.this, Notas.class));
                            }
                        }).setNegativeButton("No!", null).show();
            }
        });
        pedido.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                new AlertDialog.Builder(Pedido.this)
                        .setTitle("Confirmacion de comida lista")
                        .setMessage("El pedido esta listo?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Borrar();
                                notifyy();
                                Pedido.this.startActivity(new Intent(Pedido.this, Notas.class));
                            }
                        }).setNegativeButton("No", null).show();
            }
        });

        findViewById(R.id.imageButton7).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Pedido.this.startActivity(new Intent(Pedido.this, Notas.class));
            }
        });
    }

    public void Listar() {
        AdminSQLiteOpenHelper lista = new AdminSQLiteOpenHelper(this,"item",null,1);
        SQLiteDatabase base =lista.getReadableDatabase();
        if(base!=null){
            android.database.Cursor c =base.rawQuery("select * from item where pedido=="+dato,null);
            int cantidad=c.getCount();
            int i=0;
            String[] arreglo= new String[cantidad];
            String[] arreglo2= new String[cantidad];
            arreglo3= new Integer[cantidad];
            if(c.moveToFirst()){
                do{
                    int codigo =c.getInt(0);
                    String nombre= c.getString(1);
                    String precio= c.getString(2);
                    String pedido= c.getString(3);
                    arreglo[i] =nombre;
                    arreglo2[i]=precio;
                    arreglo3[i]=codigo;
                    i++;
                }while(c.moveToNext());
            }


            listado = (android.widget.ListView) findViewById(R.id.pedido);
            ListadoFinal otro = new ListadoFinal(this, arreglo, arreglo2);
            listado.setAdapter(otro);

        }

    }
    public void Borrar() {
        AdminSQLiteOpenHelper total = new AdminSQLiteOpenHelper(this,"item", null, 1);
        SQLiteDatabase base = total.getWritableDatabase();

        int cant = base.delete("item" ,"pedido="+dato, null); // (oficina es la nombre de la tabla, condición)
        base.close();
        if (cant == 1)
            Toast.makeText(this, "Acaba de borrar todos los platos...", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existen platos!\nAgrege Nuevamente Platos", Toast.LENGTH_SHORT).show();
    }

    private void notifyy() {
        RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();

        try {
            json.put("to", "/topics/"+"enviarNotify");
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", "Orden");
            notificacion.put("detalle", "Lista!!!");

            json.put("data", notificacion);

            String URL = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String ,String > header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAY3ArfkI:APA91bGO1vY4qop1ueQPVlguQpuMMbFwOVuBj9uzCWvB1c1al8VAa9OMyH0me5xJbOS3zXrdmmrhS406h4ZYhvLe8AuIRnjloSvJMNkfnoziMQoT7RT9KaOXE4cMnHlQNT4L-kPygVRX");
                    return header;
                }
            };

            myrequest.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
