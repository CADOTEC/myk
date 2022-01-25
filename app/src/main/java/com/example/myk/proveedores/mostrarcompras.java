package com.example.myk.proveedores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myk.R;
import com.example.myk.adaptadores.Adaptadorcompras;
import com.example.myk.clases.clasecompra;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class mostrarcompras extends AppCompatActivity {
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private ProgressDialog progressDialog;
    private ListView listacompra;
    EditText fechatxt;
    Button buscar;
    private String idproveedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarcompras);
        listacompra=(ListView) findViewById(R.id.listacompra);
        fechatxt=(EditText) findViewById(R.id.fechaetcom);
        buscar=(Button) findViewById(R.id.buscarcom);
        new task2().execute();
        idproveedor=getIntent().getExtras().getString("idproveedor");
        listacompra.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setBackgroundColor(Color.parseColor("#038C7F"));
                clasecompra selItem = (clasecompra) listacompra.getItemAtPosition(position);
                Intent intent=new Intent(mostrarcompras.this, detallelistacompras.class);
                intent.putExtra("idcompra", ""+selItem.getId());
                startActivity(intent);
                //finish();
                // CustomDialogClass cdd = new CustomDialogClass(numeros.this,""+selItem.getCantidad(),""+selItem.getId(),listanumeros,"Cantidad");

                //  cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // cdd.show();
              //  Handler handler = new Handler(); handler.postDelayed(new Runnable() { public void run() { progressDialog.dismiss(); } }, 2000); // 3000 milliseconds delay

            }
        });


    }


    private class task2 extends AsyncTask<Void,Integer, ArrayList<clasecompra>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(mostrarcompras.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected ArrayList<clasecompra> doInBackground(Void... params) {

            ArrayList<clasecompra> listacc=new ArrayList<clasecompra>();
            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("SELECT compras.id,proveedores.nombre,compras.fecha from compras,proveedores where compras.idproveedor=proveedores.id AND compras.idproveedor="+idproveedor+" ORDER BY FECHA DESC");
                while (rs.next()) {
                    //bandera += rs.getString(1);
                    listacc.add(new clasecompra(R.drawable.pedidos,rs.getString(1),rs.getString(2),rs.getString(3)));
                }
                rs.close();

                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return listacc;
        }
        @Override
        protected void onPostExecute(ArrayList<clasecompra> s) {

            Adaptadorcompras adaptadorpro=new Adaptadorcompras(mostrarcompras.this,s);
            listacompra.setAdapter(adaptadorpro);
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