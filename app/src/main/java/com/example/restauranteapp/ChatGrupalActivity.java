package com.example.restauranteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatGrupalActivity extends AppCompatActivity {

    private EditText txtMensaje;
    private TextView nombre;
    private Button btnEnviar;
    private ImageView btnAtras;
    private RecyclerView rvMensaje;

    private adaptadorMensaje adapter;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_grupal);
        nombre = findViewById(R.id.txtNombre);
        rvMensaje = findViewById(R.id.rvMensajes);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnAtras = findViewById(R.id.btnAtras);

        String name = getIntent().getStringExtra("nombre2");
        nombre.setText(name);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ChatGrupalActivity.this, principal.class);
                startActivity(next);
            }
        });

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");

        adapter = new adaptadorMensaje(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensaje.setLayoutManager(l);
        rvMensaje.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txtMensaje.getText().toString().isEmpty()){
                    Date d = new Date();
                    SimpleDateFormat dat = new SimpleDateFormat("hh:mm a");
                    String hora = dat.format(d);

                    databaseReference.push().setValue(new mensaj(txtMensaje.getText().toString(),nombre.getText().toString(),"","1", hora));
                    txtMensaje.setText("");
                }else{
                    Toast.makeText(ChatGrupalActivity.this, "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                }
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                mensaj m = dataSnapshot.getValue(mensaj.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScrollbar(){
        rvMensaje.scrollToPosition(adapter.getItemCount() - 1);
    }
}