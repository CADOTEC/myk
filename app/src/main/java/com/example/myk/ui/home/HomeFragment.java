package com.example.myk.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myk.adaptadores.Adaptadorproveedores;
import com.example.myk.R;
import com.example.myk.clases.claseproveedores;
import com.example.myk.databinding.FragmentHomeBinding;
import com.example.myk.proveedores.mostrarproveedores;
import com.example.myk.ubicacionf;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ListView listaproveedores;
    EditText buscarproveedorestxt;
    Button buscarpro;
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private ProgressDialog progressDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ubicacionf.myVariable2="PROVEEDORES";
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Proveedores");
        buscarproveedorestxt =(EditText) root.findViewById(R.id.txtbuscarproveedores);
        listaproveedores = (ListView) root.findViewById(R.id.listaproveedores);
        buscarpro = (Button) root.findViewById(R.id.buscarpro);
       // navUsername.setText(""+ Global.myVariable);
        llenarlistview();


        buscarpro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
if(buscarproveedorestxt.getText().toString().equals("")==false) {
    listaproveedores.setAdapter(null);
new task1().execute();

Toast.makeText(getContext(), "Resultados para: "+buscarproveedorestxt.getText().toString() , Toast.LENGTH_SHORT).show();


}else{
    listaproveedores.setAdapter(null);
  new task2().execute();
}


            }
        });


        listaproveedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                view.setBackgroundColor(Color.rgb(137,196,214));
                claseproveedores selItem = (claseproveedores) listaproveedores.getItemAtPosition(position);
                Intent intent=new Intent(getContext(), mostrarproveedores.class);
                intent.putExtra("DATO", ""+selItem.getId());
                startActivity(intent);

            }
        });
        return root;
    }
    private class task1 extends AsyncTask< Void,Integer, ArrayList<claseproveedores>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(getContext());
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progressbuscar);
        }

        @Override
        protected ArrayList<claseproveedores> doInBackground(Void... params) {

            ArrayList<claseproveedores> listacc=new ArrayList<claseproveedores>();

            listacc=buscar(buscarproveedorestxt.getText().toString());

            return listacc;
        }
        @Override
        protected void onPostExecute( ArrayList<claseproveedores> s) {
            Adaptadorproveedores adaptadorpro=new Adaptadorproveedores(getContext(),s);
            listaproveedores.setAdapter(adaptadorpro);
            progressDialog.dismiss();

        }
    }

    private class task2 extends AsyncTask<Void,Integer, ArrayList<claseproveedores>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(getContext());
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progressbuscar);
        }

        @Override
        protected ArrayList<claseproveedores> doInBackground(Void... params) {

            ArrayList<claseproveedores> listacc=new ArrayList<claseproveedores>();
            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("Select * from proveedores");

                while (rs.next()) {
                    //bandera += rs.getString(1);
                    listacc.add(new claseproveedores(R.drawable.imgpro,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                }
                rs.close();
                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }

            return listacc;
        }
        @Override
        protected void onPostExecute(ArrayList<claseproveedores> s) {
            progressDialog.dismiss();
            Adaptadorproveedores adaptadorpro=new Adaptadorproveedores(getContext(),s);
            listaproveedores.setAdapter(adaptadorpro);
        }
    }

    private void llenarlistview(){
        listaproveedores.setAdapter(null);
        ArrayList<claseproveedores> listacc=new ArrayList<claseproveedores>();


        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from proveedores");

            while (rs.next()) {
                //bandera += rs.getString(1);
                listacc.add(new claseproveedores(R.drawable.imgpro,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        Adaptadorproveedores adaptadorpro=new Adaptadorproveedores(getContext(),listacc);
        listaproveedores.setAdapter(adaptadorpro);
    }
    private ArrayList<claseproveedores> buscar(String nombre){

        ArrayList<claseproveedores> listacc=new ArrayList<claseproveedores>();


        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from proveedores where nombre like '%"+nombre+"%' OR dnioruc like '%"+nombre+"%'");


            while (rs.next()) {
                //bandera += rs.getString(1);
                listacc.add(new claseproveedores(R.drawable.imgpro,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
return listacc;
    }
    public Connection CONN()
    {
        final String class_jdbc = "com.mysql.jdbc.Driver";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            Class.forName(class_jdbc);
            conn = (Connection) DriverManager.getConnection(gURL + gIP + ":" + gPORT + "/" + gDATABASE, gUSR, gPSW);
        } catch (SQLException se) {
            Log.e("ERROR1", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR3", e.getMessage());
        }
        return conn;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}