package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class detallelistacompras extends AppCompatActivity {
private String idcompra;
private TextView proveedor,fecha,guia,pesador,observacion,encargado;
private String proveedors="",fechas="",guias="",pesadors="",observacions="",encargados="";
private ListView listadetallecompras;
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallelistacompras);
        idcompra=getIntent().getExtras().getString("idcompra");
        listadetallecompras=(ListView)findViewById(R.id.listadelicom);

        proveedor=(TextView)findViewById(R.id.proveedordelico);
        fecha=(TextView)findViewById(R.id.fechadelicom);
        guia=(TextView)findViewById(R.id.guiadelicom);
        pesador=(TextView)findViewById(R.id.recibiodelicom);
        observacion=(TextView)findViewById(R.id.observaciondelicom);
        encargado=(TextView)findViewById(R.id.encargadodelicom);
      //  Toast.makeText(getApplicationContext(), ""+idcompra, Toast.LENGTH_SHORT).show();
new task2().execute();

        listadetallecompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setBackgroundColor(Color.parseColor("#038C7F"));
                clasepesadas selItem = (clasepesadas) listadetallecompras.getItemAtPosition(position);

                Intent intent=new Intent(detallelistacompras.this, listadetallepesadas.class);
                intent.putExtra("idcompra", ""+idcompra);
                intent.putExtra("tipo", ""+selItem.getNjabas());
                startActivity(intent);


            }
        });

    }


    private class task2 extends AsyncTask<Void,Integer, ArrayList<clasepesadas>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(detallelistacompras.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected ArrayList<clasepesadas> doInBackground(Void... params) {

            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();
                ResultSet rs = st.executeQuery("SELECT compras.id,proveedores.nombre,compras.fecha,compras.observacion,compras.guia,compras.encargado,compras.recibio FROM compras, proveedores WHERE compras.idproveedor=proveedores.id AND compras.id='"+idcompra+"'");
                while (rs.next()) {
                    if(rs.getString(4)==null){
                        observacions="Sin observaciones";
                    }else{
                        observacions=""+rs.getString(4);
                    }

                    if(rs.getString(5)==null){
                        guias="-";
                    }else{
                        guias=""+rs.getString(5);
                    }

                    if(rs.getString(6)==null){
                        encargados="-";
                    }else{
                        encargados=""+rs.getString(6);
                    }

                    proveedors=""+rs.getString(2);
                    fechas=""+rs.getString(3);
                    pesadors=""+rs.getString(7);


                }
                rs.close();
                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }


            ArrayList<clasepesadas> listacc=new ArrayList<clasepesadas>();
            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("Select * from pesadas where idcompra='"+idcompra+"'");
                while (rs.next()) {
                    //bandera += rs.getString(1);
                    listacc.add(new clasepesadas(R.drawable.rpesada,rs.getString(1),rs.getString(3),rs.getString(4)+"KG",rs.getString(5),rs.getString(6)));
                }
                rs.close();


                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return listacc;
        }
        @Override
        protected void onPostExecute(ArrayList<clasepesadas> s) {
            proveedor.setText(proveedors);
            fecha.setText(fechas);
            observacion.setText(observacions);
            guia.setText(guias);
            pesador.setText(pesadors);
            encargado.setText(encargados);
            progressDialog.dismiss();


            Adaptadorpesadas adaptadorpro=new Adaptadorpesadas(detallelistacompras.this,s);
            listadetallecompras.setAdapter(adaptadorpro);
            progressDialog.dismiss();
        }
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
}