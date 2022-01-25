package com.example.myk.ui.gallery;

import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myk.R;
import com.example.myk.adaptadores.Adaptadorpesadas;
import com.example.myk.adaptadores.Adaptadorclientes;
import com.example.myk.clases.clasepesadas;
import com.example.myk.clases.claseclientes;
import com.example.myk.databinding.FragmentGalleryBinding;
import com.example.myk.ingresarpedidopro;
import com.example.myk.ubicacionf;
import com.example.myk.ui.home.HomeFragment;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    ListView listaclientes;
    EditText buscarclienteset;
    Button buscarcli;
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private ProgressDialog progressDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        ubicacionf.myVariable2="CLIENTES";
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Clientes");
        buscarclienteset =(EditText) root.findViewById(R.id.buscarcliente);
        listaclientes = (ListView) root.findViewById(R.id.lvclientes);
        buscarcli = (Button) root.findViewById(R.id.btnbuscarcliente);
     /*  final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        listaclientes.setAdapter(null);
        new task2().execute();


        buscarcli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(buscarclienteset.getText().toString().equals("")==false) {
                    listaclientes.setAdapter(null);
                    new task1().execute();

                    Toast.makeText(getContext(), "Resultados para: "+buscarproveedorestxt.getText().toString() , Toast.LENGTH_SHORT).show();


                }else{
                    listaclientes.setAdapter(null);
                    new task2().execute();
                }


            }
        });



        return root;
    }



    private class task2 extends AsyncTask<Void,Integer, ArrayList<claseclientes>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(getContext());
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected ArrayList<claseclientes> doInBackground(Void... params) {

            ArrayList<claseclientes> listacc=new ArrayList<claseclientes>();


            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("Select * from clientes");

                while (rs.next()) {
                    listacc.add(new claseclientes(R.drawable.clientesimagen,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                }
                rs.close();
                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }

            return listacc;
        }
        @Override
        protected void onPostExecute(ArrayList<claseclientes> s) {
            Adaptadorclientes adaptadorpro=new Adaptadorclientes(getContext(),s);
            listaclientes.setAdapter(adaptadorpro);
            progressDialog.dismiss();
        }
    }


   /* private void llenarlistview(){
        listaclientes.setAdapter(null);
        ArrayList<claseproveedores> listacc=new ArrayList<claseproveedores>();


        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from clientes");

            while (rs.next()) {
                listacc.add(new clase(R.drawable.clientesimagen,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7)));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        Adaptadorproveedores adaptadorpro=new Adaptadorproveedores(getContext(),listacc);
        listaclientes.setAdapter(adaptadorpro);
    }*/
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