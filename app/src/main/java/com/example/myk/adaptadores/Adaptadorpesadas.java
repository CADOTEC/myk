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
import com.example.myk.clases.clasepesadas;

import java.util.ArrayList;

public class Adaptadorpesadas implements ListAdapter {
    private Context context;
    private ArrayList<clasepesadas> listitems;

    public Adaptadorpesadas(Context context, ArrayList<clasepesadas> listitems) {
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
        clasepesadas item=(clasepesadas) getItem(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.viewpesadas,null);
        ImageView imgfoto=(ImageView)convertView.findViewById(R.id.imgvpesadas);
        TextView id=(TextView)convertView.findViewById(R.id.idvpesadas);
        TextView polloogallina=(TextView)convertView.findViewById(R.id.gallinaopollvpesadas);
        TextView peso=(TextView)convertView.findViewById(R.id.pesovpesadas);
        TextView njabas=(TextView)convertView.findViewById(R.id.njabasvpesadas);
        TextView naves=(TextView)convertView.findViewById(R.id.navesvpesadas);


        imgfoto.setImageResource(item.getImg());
        id.setText(item.getId());
        polloogallina.setText(item.getPolloogallina());
        peso.setText(item.getPeso());
        njabas.setText(item.getNjabas());
        naves.setText(item.getNaves());

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
