package com.example.restauranteapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class ListadoFinal extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] item_name;
    private final String[] item_precio;



    public ListadoFinal(Activity context, String[] item_name, String[] item_precio) {
        super(context, R.layout.listadofinal, item_name);
        this.context = context;
        this.item_name = item_name;
        this.item_precio = item_precio;


    }


    public android.view.View getView(int position, android.view.View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        android.view.View rowView = inflater.inflate(R.layout.listadofinal, null, true);


        android.widget.TextView txtitem = (android.widget.TextView) rowView.findViewById(R.id.item);
        android.widget.TextView txtprecio = (android.widget.TextView) rowView.findViewById(R.id.itempre);

        txtitem.setText(item_name[position]);
        txtprecio.setText(item_precio[position]);

        return rowView;


    }
}
