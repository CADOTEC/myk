package com.example.myk;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptadorproveedores implements ListAdapter {
    private Context context;
    private ArrayList<claseproveedores> listitems;

    public Adaptadorproveedores(Context context, ArrayList<claseproveedores> listitems) {
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
        claseproveedores item=(claseproveedores) getItem(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.viewpro,null);
        ImageView imgfoto=(ImageView)convertView.findViewById(R.id.imgv);
        TextView id=(TextView)convertView.findViewById(R.id.idtxt);
        TextView dnioruc=(TextView)convertView.findViewById(R.id.dnioructxt);
        TextView nombre=(TextView)convertView.findViewById(R.id.nombretxt);
        TextView estado=(TextView)convertView.findViewById(R.id.estadotxt);


        imgfoto.setImageResource(item.getImg());
        id.setText(item.getId());
        dnioruc.setText(item.getDnioruc());
        nombre.setText(item.getNombre());
        estado.setText(item.getEstado());

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
