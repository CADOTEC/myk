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
import com.example.myk.clases.clasecompra;

import java.util.ArrayList;

public class Adaptadorcompras implements ListAdapter {
    private Context context;
    private ArrayList<clasecompra> listitems;

    public Adaptadorcompras(Context context, ArrayList<clasecompra> listitems) {
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
        clasecompra item=(clasecompra) getItem(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.viewcompra,null);
        ImageView imgfoto=(ImageView)convertView.findViewById(R.id.imgcom);
        TextView id=(TextView)convertView.findViewById(R.id.idtxtcom);
        TextView nombre=(TextView)convertView.findViewById(R.id.nombretxtcom);
        TextView fecha=(TextView)convertView.findViewById(R.id.fechatxtcom);


        imgfoto.setImageResource(item.getImg());
        id.setText(item.getId());
        nombre.setText(item.getNombre());
        fecha.setText(item.getFecha());

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
