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
import android.widget.Spinner;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class agregarproveedores extends AppCompatActivity {
    private String dnitxt="";
    private String nombretxt="";
    private String direcciontxt="";
    private  String estadotxt="";
    private  String telefono1txt="";
    private  String telefono2txt="";
    private  String correotxt="";
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
   private EditText dni;
    private EditText     nombre;
    private EditText     direccion;
    private EditText     telefono1;
    private EditText     telefono2;
    private EditText     correo;
    private  Spinner estadospin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproveedores);
        Button     agregar=(Button)findViewById(R.id.agregarprobtn);
         dni=(EditText)findViewById(R.id.agdniopro);
             nombre=(EditText)findViewById(R.id.agnombretxt);
        direccion=(EditText)findViewById(R.id.agdireccionprotxt);
        telefono1=(EditText)findViewById(R.id.agtelefono1protxt);
        telefono2=(EditText)findViewById(R.id.agtelefono2protxt);
        correo=(EditText)findViewById(R.id.agcorreoprotxt);
         estadospin=(Spinner)findViewById(R.id.spinner);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dnitxt=""+dni.getText().toString();
                direcciontxt=""+direccion.getText().toString();
                estadotxt=""+estadospin.getSelectedItem().toString();
                nombretxt=""+nombre.getText().toString();
                telefono1txt=""+telefono1.getText().toString();
                telefono2txt=""+telefono2.getText().toString();
                correotxt=""+correo.getText().toString();
                if(dnitxt.equals("")==false&&direcciontxt.equals("")==false&&direcciontxt.equals("")==false){
insertar(nombre.getText().toString(),dni.getText().toString(),direccion.getText().toString(),estadospin.getSelectedItem().toString(),telefono1.getText().toString(),telefono2.getText().toString(),correo.getText().toString());

                }else {
                    Toast.makeText(getApplicationContext(), "Debe completar todos los campos!", Toast.LENGTH_SHORT).show();

                }

                      /*  Intent intent=new Intent(agregarproveedores.this,Principal2Activity.class);
                        startActivity(intent);
                        finish();*/






            }
        });
    }

    private void insertar(String nombrev,String dniorucv,String direccionv,String estadov,String telefono1v,String telefono2v,String correov){
       // Toast.makeText(getApplicationContext(), ""+nombrev+"-"+dniorucv+"-"+direccionv+"-"+estadov, Toast.LENGTH_SHORT).show();
       try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO proveedores(dnioruc,direccion,nombre,estado,telefono1,telefono2,correo) VALUES('"+dniorucv+"','"+direccionv+"','"+nombrev+"','"+estadov+"','"+telefono1v+"','"+telefono2v+"','"+correov+"')");

            ConnexionMySQL.close();
             Toast.makeText(getApplicationContext(), "AGREGADO!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();

        }
        dni.setText("");
        nombre.setText("");
        direccion.setText("");
        estadospin.setSelection(0);
        telefono1.setText("");
        telefono2.setText("");
        correo.setText("");

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