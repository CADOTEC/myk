package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class mostrarproveedores extends AppCompatActivity {
    String dniorucs="";
    String nombre="";
    String direccion="";
    String estado="";
    String telefono1="";
    String telefono2="";
    String correo="";
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    String ba="";
    TextView nombretxt;
    TextView dnioructxt;
    TextView direcciontxt;
    TextView estadotxt;
    TextView telefono1txt;
    TextView telefono2txt;
    TextView correotxt;
    Button ingresarbtn;
    Button mostrarcompra;
    private String id="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarproveedores);
        nombretxt = (TextView) findViewById(R.id.nombreprotxt);
        dnioructxt = (TextView) findViewById(R.id.dniorucprotxt);
        direcciontxt = (TextView) findViewById(R.id.direccionprotxt);
        estadotxt = (TextView) findViewById(R.id.estadoprotxt);
        telefono1txt = (TextView) findViewById(R.id.telefono1protxt);
        telefono2txt = (TextView) findViewById(R.id.telefono2protxt);
        correotxt = (TextView) findViewById(R.id.correoprotxt);
        ingresarbtn = (Button) findViewById(R.id.ingresarpedidorebtn);
        mostrarcompra = (Button) findViewById(R.id.mostrarcomprabtnmopro);

         id=getIntent().getExtras().getString("DATO");
      //  buscar(id);
new task1().execute();

        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mostrarproveedores.this,ingresarpedidopro.class);
                intent.putExtra("idproveedor", ""+getIntent().getExtras().getString("DATO"));
                startActivity(intent);
                finish();
            }
        });
        mostrarcompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mostrarproveedores.this,mostrarcompras.class);
                intent.putExtra("idproveedor", ""+getIntent().getExtras().getString("DATO"));
                startActivity(intent);
                finish();
            }
        });

    }


    private class task1 extends AsyncTask<Void, Integer, claseproveedorescompleto>  {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(mostrarproveedores.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected claseproveedorescompleto doInBackground(Void... params) {
            claseproveedorescompleto c=new claseproveedorescompleto();
            String telefono1="",telefono2="",correoin="";
            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("Select * from proveedores where id='"+id+"'");
                while (rs.next()) {

                    if(rs.getString(6)==null){
                        telefono1="Sin teléfono";
                    }else{
                        telefono1=""+rs.getString(6);
                    }

                    if(rs.getString(7)==null){
                        telefono2="Sin teléfono";
                    }else{
                        telefono2=""+rs.getString(7);
                    }
                    if(rs.getString(8)==null){
                        correoin="Sin correo";
                    }else{
                        correoin=""+rs.getString(8);
                    }
                    c=new claseproveedorescompleto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),telefono1,telefono2,correoin);

                }
                rs.close();
                ConnexionMySQL.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return c;
        }



        @Override
        protected void onPostExecute(claseproveedorescompleto s) {
            direcciontxt.setText(""+s.getDireccion());
            nombretxt.setText(""+s.getNombre());
            dnioructxt.setText(""+s.getDnioruc());

            estadotxt.setText(""+s.getEstado());
            telefono1txt.setText(""+s.getTelefono1());
          telefono2txt.setText(""+s.getTelefono2());
            correotxt.setText(""+s.getCorreo());
            progressDialog.dismiss();

            }
        }


    private void buscar(String nombre){

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from proveedores where id='"+nombre+"'");
            while (rs.next()) {
                nombretxt.setText(""+rs.getString(4));
                dnioructxt.setText(""+rs.getString(2));
                direcciontxt.setText(""+rs.getString(3));
                estadotxt.setText(""+rs.getString(5));
                telefono1txt.setText(""+rs.getString(6));
                if(rs.getString(7).equals("null")){telefono2txt.setText("Sin teléfono 2");}else{telefono2txt.setText(""+rs.getString(7));}
                correotxt.setText(""+rs.getString(8));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

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