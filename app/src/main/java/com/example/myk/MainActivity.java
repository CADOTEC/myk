package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
progressDialog.show();
progressDialog.setContentView(R.layout.progress_dialog);

                ba="";
                ba=""+validar(usuario.getText().toString(),pass.getText().toString());
                // Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectas."+b, Toast.LENGTH_SHORT).show();

                if(ba.equals("0")){
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectas.", Toast.LENGTH_SHORT).show();
                    Global.myVariable="";
                    progressDialog.dismiss();
                }else{
                    if(ba.equals("1")){
                        Toast.makeText(getApplicationContext(), "Bienvenido "+usuariov, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Principal2Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
//ejecutarServicio("https://demo.e.cadotecperu.com/prueba/insertar.php");
                Handler handler = new Handler(); handler.postDelayed(new Runnable() { public void run() { progressDialog.dismiss(); } }, 2000); // 3000 milliseconds delay

            }
        });
    }

/*public void onBackPressed(){
        progressDialog.dismiss();
}*/
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "EXITO", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }

    }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("id","12");
                parametros.put("nombre","34");
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public String validar(String usuario,String contraseña){
        String bandera="";
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("SELECT count(*),nombresyapellidos from usuarios WHERE dni like '"+usuario+"' and pass like '"+contraseña+"'");
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