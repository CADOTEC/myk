package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class detallepesadalista extends AppCompatActivity {
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    private EditText peso;
    private EditText naves;
    private EditText njabas;
    private Button eliminar;
    private CheckBox gallina;
    private CheckBox pollo;
    private CheckBox gallo;
    private CheckBox pollox;
    private CheckBox polloy;
    private Button actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallepesadalista);
        String idd=getIntent().getExtras().getString("DATO");
        peso=(EditText) findViewById(R.id.pesoetdepeli);
        njabas=(EditText) findViewById(R.id.njabasetdepeli);
        naves=(EditText) findViewById(R.id.navesetdepeli);

        gallina=(CheckBox) findViewById(R.id.chgallinadepeli);
        pollo=(CheckBox) findViewById(R.id.chpollodepeli);
        pollox=(CheckBox) findViewById(R.id.chpolloxdepeli);
        polloy=(CheckBox) findViewById(R.id.chpolloyydepeli);
        gallo=(CheckBox) findViewById(R.id.chgallodepeli);
        eliminar=(Button) findViewById(R.id.eliminarbtndepeli); actualizar=(Button) findViewById(R.id.actualizarbtndepeli);
        llenarlistview(idd);

        gallina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pollo.setChecked(false);
                pollox.setChecked(false);
                polloy.setChecked(false);
                gallo.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#F2C12E"));
                peso.setHighlightColor(Color.parseColor("#F2C12E"));
                peso.setTextColor(Color.parseColor("#F2C12E"));
                njabas.setTextColor(Color.parseColor("#F2C12E"));
                naves.setTextColor(Color.parseColor("#F2C12E"));
                if(pollo.isChecked()==false && pollox.isChecked()==false && polloy.isChecked()==false && gallo.isChecked()==false && gallina.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njabas.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        gallo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallina.setChecked(false);
                pollo.setChecked(false);
                polloy.setChecked(false);
                pollox.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#FF0000"));
                peso.setHighlightColor(Color.parseColor("#FF0000"));
                peso.setTextColor(Color.parseColor("#FF0000"));
                njabas.setTextColor(Color.parseColor("#FF0000"));
                naves.setTextColor(Color.parseColor("#FF0000"));
                if(pollo.isChecked()==false && pollox.isChecked()==false && polloy.isChecked()==false && gallo.isChecked()==false && gallina.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njabas.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });

        pollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallina.setChecked(false);
                pollox.setChecked(false);
                polloy.setChecked(false);
                gallo.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#EF5B2D"));
                peso.setHighlightColor(Color.parseColor("#EF5B2D"));
                peso.setTextColor(Color.parseColor("#EF5B2D"));
                njabas.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setTextColor(Color.parseColor("#EF5B2D"));
                if(pollo.isChecked()==false && pollox.isChecked()==false && polloy.isChecked()==false && gallo.isChecked()==false && gallina.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njabas.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        pollox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallina.setChecked(false);
                pollo.setChecked(false);
                polloy.setChecked(false);
                gallo.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#025959"));
                peso.setHighlightColor(Color.parseColor("#025959"));
                peso.setTextColor(Color.parseColor("#025959"));
                njabas.setTextColor(Color.parseColor("#025959"));
                naves.setTextColor(Color.parseColor("#025959"));
                if(pollo.isChecked()==false && pollox.isChecked()==false && polloy.isChecked()==false && gallo.isChecked()==false && gallina.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njabas.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        polloy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallina.setChecked(false);
                pollo.setChecked(false);
                pollox.setChecked(false);
                gallo.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#2d4d9f"));
                peso.setHighlightColor(Color.parseColor("#2d4d9f"));
                peso.setTextColor(Color.parseColor("#2d4d9f"));
                njabas.setTextColor(Color.parseColor("#2d4d9f"));
                naves.setTextColor(Color.parseColor("#2d4d9f"));
                if(pollo.isChecked()==false && pollox.isChecked()==false && polloy.isChecked()==false && gallo.isChecked()==false && gallina.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njabas.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });

    }

    //llenar datos
    private void llenarlistview(String id){

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from detallepesada where id=+"+id);


            while (rs.next()) {


                      // rs.getString(3);
                if(rs.getString(3).equals("GALLINA")){
                    gallina.setChecked(true);
                }
                if(rs.getString(3).equals("POLLO")){
                    pollo.setChecked(true);
                }
                if(rs.getString(3).equals("POLLO X")){
                    pollox.setChecked(true);
                }
                if(rs.getString(3).equals("POLLO Y")){
                    polloy.setChecked(true);
                }
                if(rs.getString(3).equals("GALLO")){
                    gallo.setChecked(true);
                }
                peso.setText(""+rs.getString(4));
                njabas.setText(""+rs.getString(5));
                naves.setText(""+rs.getString(6));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

//obtener conexion
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