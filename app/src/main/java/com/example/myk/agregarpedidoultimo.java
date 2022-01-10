package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class agregarpedidoultimo extends AppCompatActivity {
    private TextView pbga,pbpo,pbgo,pbpx,pbpy;
    private String jg,cg,jp,cp,jpx,cpx,jpy,cpy,jgo,cgo;
    private double j=0,g=0,go=0,px=0,py=0,p=0;
    private Button guardar;
    EditText guiaet,encargadoet,observacionet;
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private String idproveedor;
    private String idcom="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarpedidoultimo);
        pbga=(TextView)findViewById(R.id.pesobrutogaagpeul);
        pbpo=(TextView)findViewById(R.id.pesobrutopoagpeul);
        pbgo=(TextView)findViewById(R.id.pesobrutogalloagpeul);
        pbpx=(TextView)findViewById(R.id.pesobrutopolloxagpeul);
        pbpy=(TextView)findViewById(R.id.pesobrutopolloyagpeul);
        guiaet=(EditText)findViewById(R.id.guiaetagpeul);
        encargadoet=(EditText)findViewById(R.id.encargadotxtagpeul);
        observacionet=(EditText)findViewById(R.id.observacionagpeul);
        guardar=(Button) findViewById(R.id.guardarbtnagpeul);

//recibiendo pesos
        pbga.setText(getIntent().getExtras().getString("tgallinas"));
        g=Double.parseDouble(getIntent().getExtras().getString("tgallinas"));

        pbpo.setText(getIntent().getExtras().getString("tpollos"));
        p=Double.parseDouble(getIntent().getExtras().getString("tpollos"));

        pbgo.setText(getIntent().getExtras().getString("tgallo"));
        go=Double.parseDouble(getIntent().getExtras().getString("tgallo"));

        pbpx.setText(getIntent().getExtras().getString("tpollox"));
        px=Double.parseDouble(getIntent().getExtras().getString("tpollox"));

        pbpy.setText(getIntent().getExtras().getString("tpolloy"));
        py=Double.parseDouble(getIntent().getExtras().getString("tpolloy"));
        idproveedor=getIntent().getExtras().getString("idproveedor");
        //recibiendo cantidad de jabas
        jg=getIntent().getExtras().getString("jg");
        jp=getIntent().getExtras().getString("jp");
        jgo=getIntent().getExtras().getString("jgo");
        jpx=getIntent().getExtras().getString("jpx");
        jpy=getIntent().getExtras().getString("jpy");

        //recibiendo cantidad de aves
        cg=getIntent().getExtras().getString("cg");
        cp=getIntent().getExtras().getString("cp");
        cgo=getIntent().getExtras().getString("cgo");
        cpx=getIntent().getExtras().getString("cpx");
        cpy=getIntent().getExtras().getString("cpy");

       /* double pesoga=0;
        double pesopo=0;
        double japo=0;
        double jaga=0;
        pesoga=Double.parseDouble(valorg);
        pesopo=Double.parseDouble(valorp);

        japo=Double.parseDouble(jp);
        jaga=Double.parseDouble(jg);
        japo=japo*6.850;
        jaga=jaga*6.850;

        prga.setText(""+jaga);
        prpo.setText(""+japo);*/

        //boton guardar
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(encargadoet.getText().toString().equals("")==false) {


                 new task1().execute("hola");


                }else{
                    Toast.makeText(getApplicationContext(), "Debe ingresar un ENCARGADO", Toast.LENGTH_SHORT).show();
                }





            }
        });
    }

    private class task1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(agregarpedidoultimo.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected String doInBackground(String... strings) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateandTime = simpleDateFormat.format(new Date());
            insertarcompra(idproveedor, currentDateandTime, observacionet.getText().toString(), guiaet.getText().toString(), encargadoet.getText().toString());
            idcom = encontraridcompra(currentDateandTime, idproveedor);

            if (g > 0 || p > 0 || go > 0 || px > 0 || py > 0) {
                if (g > 0) {
                    insertar("" + g, "" + jg, "" + cg, "GALLINA", "" + idcom);
                }
                if (p > 0) {
                    insertar("" + p, "" + jp, "" + cp, "POLLO", "" + idcom);
                }
                if (px > 0) {
                    insertar("" + px, "" + jpx, "" + cpx, "POLLO X", "" + idcom);
                }
                if (py > 0) {
                    insertar("" + py, "" + jpy, "" + cpx, "POLLO Y", "" + idcom);
                }
                if (go > 0) {
                    insertar("" + go, "" + jgo, "" + cgo, "GALLO", "" + idcom);
                }
            }
            actualizarpesadas();
            return strings[0];
        }
        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getApplicationContext(), "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(agregarpedidoultimo.this,Principal2Activity.class);
                startActivity(intent);
                finish();
            progressDialog.dismiss();
        }
    }

private void actualizarpesadas(){
    try {
        Connection ConnexionMySQL = CONN();
        Statement st = ConnexionMySQL.createStatement();

        st.executeUpdate("UPDATE pesadas SET idcompra="+idcom+" WHERE idcompra=0");
        st.executeUpdate("UPDATE detallepesada SET idcompra="+idcom+" WHERE idcompra=0");

        ConnexionMySQL.close();

    } catch (Exception e) {
        e.printStackTrace();

    }
}


    private void insertar(String peso,String cantidaddejabas,String cantidadaves,String tipo,String idcompra){
        // Toast.makeText(getApplicationContext(), ""+nombrev+"-"+dniorucv+"-"+direccionv+"-"+estadov, Toast.LENGTH_SHORT).show();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO pesadas(peso,cantidaddejabas,cantidadaves,tipo,idcompra) VALUES('"+peso+"','"+cantidaddejabas+"','"+cantidadaves+"','"+tipo+"',0)");

            ConnexionMySQL.close();
            //  Toast.makeText(getApplicationContext(), "AGREGADO!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }




    private String encontraridcompra(String fecha,String idpro){
        String idcompra="0";
        try {

            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("SELECT idproveedor FROM compras WHERE fecha='"+fecha+"' AND idproveedor='"+idpro+"'");


            while (rs.next()) {
                //bandera += rs.getString(1);
                idcompra=rs.getString(1);

            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return idcompra;
    }
    private void insertarcompra(String idproveedor,String fecha,String observacion,String guia,String encargado){
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO compras(idproveedor,fecha,observacion,guia,encargado,recibio) VALUES('"+idproveedor+"','"+fecha+"','"+observacion+"','"+guia+"','"+encargado+"','"+Global.myVariable+"')");

            ConnexionMySQL.close();
            //  Toast.makeText(getApplicationContext(), "AGREGADO!", Toast.LENGTH_SHORT).show();

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