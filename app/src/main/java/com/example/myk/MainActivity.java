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
import android.widget.Toast;

import com.example.myk.proveedores.Principal2Activity;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";
    String ba="";
    EditText usuario;
    EditText pass;
    Button ingresarbtn;
    String usuariov="";
    String tipov="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=(EditText)findViewById(R.id.username4);
        pass=(EditText)findViewById(R.id.password3);
        ingresarbtn=(Button)findViewById(R.id.ingresarbtn);

        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new task1().execute("hola");

            }
        });
    }


    private class task1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progressvalidar);
        }

        @Override
        protected String doInBackground(String... strings) {
            ba="";
            ba=""+validar(usuario.getText().toString(),pass.getText().toString());
            if(ba.equals("1")){

                Intent intent=new Intent(MainActivity.this, Principal2Activity.class);
                startActivity(intent);
                finish();
            }
            return strings[0];
        }



        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(ba.equals("0")){
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectas.", Toast.LENGTH_SHORT).show();
                Global.myVariable="";

            }else{
                if(ba.equals("1")){
                    Toast.makeText(getApplicationContext(), "Bienvenido "+usuariov, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    public String validar(String usuario,String contraseña){
        String bandera="";
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("SELECT count(*),nombresyapellidos,tipo from usuarios WHERE dni like '"+usuario+"' and pass like '"+contraseña+"'");
            Global.myVariable="";

            while (rs.next()) {
                bandera += rs.getString(1);
                usuariov=""+rs.getString(2);
                Global.myVariable=""+usuariov;
                tipov=""+rs.getString(3);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }



        return bandera;
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