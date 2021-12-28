package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
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
        String id=getIntent().getExtras().getString("DATO");
        buscar(id);


        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mostrarproveedores.this,ingresarpedidopro.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void buscar(String nombre){

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from proveedores where id='"+nombre+"'");


            while (rs.next()) {
                //bandera += rs.getString(4);
                nombre=""+rs.getString(4);
                 dniorucs=""+rs.getString(2);
                 direccion=""+rs.getString(3);
                 estado=""+rs.getString(5);
                telefono1=""+rs.getString(6);
                telefono2=""+rs.getString(7);
                correo=""+rs.getString(8);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        nombretxt.setText(""+nombre);
        dnioructxt.setText(""+dniorucs);
        direcciontxt.setText(""+direccion);
        estadotxt.setText(""+estado);
        telefono1txt.setText(""+telefono1);
        if(telefono2.equals("null")){telefono2txt.setText("Sin teléfono 2");}else{telefono2txt.setText(""+telefono2);}

        correotxt.setText(""+correo);


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