package com.example.myk.adaptadores;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myk.R;
import com.example.myk.clases.claseclientes;

import java.util.ArrayList;

public class Adaptadorclientes implements ListAdapter {
    private Context context;
    private ArrayList<claseclientes> listitems;

    public Adaptadorclientes(Context context, ArrayList<claseclientes> listitems) {
        this.context = context;
        this.listitems = listitems;
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    // @Override
    public int getCount() {
        return listitems.size();
    }

    //@Override
    public Object getItem(int position) {
        return listitems.get(position);
    }

    //@Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        claseclientes item=(claseclientes) getItem(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.viewcli,null);
        ImageView imgfoto=(ImageView)convertView.findViewById(R.id.imgvicli);
        TextView id=(TextView)convertView.findViewById(R.id.idvicli);
        TextView dnioruc=(TextView)convertView.findViewById(R.id.dnivicli);
        TextView nombre=(TextView)convertView.findViewById(R.id.nombrevicli);
        TextView telefono=(TextView)convertView.findViewById(R.id.telefonovicli);
        TextView direccion=(TextView)convertView.findViewById(R.id.direccionvicli);


        imgfoto.setImageResource(item.getImg());
        id.setText(item.getId());
        dnioruc.setText(item.getDni());
        nombre.setText(item.getNombreyapellido());
        telefono.setText(item.getTelefono());
        direccion.setText(item.getDireccion());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
