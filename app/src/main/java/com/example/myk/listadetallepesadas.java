package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class listadetallepesadas extends AppCompatActivity {
private ListView listapesadas;
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
        setContentView(R.layout.activity_listadetallepesadas);
        listapesadas=(ListView)findViewById(R.id.listadepe);
        listapesadas.setAdapter(null);
        new task2().execute();

        Toast.makeText(getApplicationContext(), ""+ getIntent().getExtras().getString("tipo"), Toast.LENGTH_SHORT).show();
    }

    private class task2 extends AsyncTask<Void,Integer, ArrayList<clasepesadas>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(listadetallepesadas.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected ArrayList<clasepesadas> doInBackground(Void... params) {

            ArrayList<clasepesadas> listacc=new ArrayList<clasepesadas>();
            try {
                Connection ConnexionMySQL = CONN();
                Statement st = ConnexionMySQL.createStatement();

                ResultSet rs = st.executeQuery("Select * from detallepesada where idcompra='"+getIntent().getExtras().getString("idcompra")+"' AND gallinaopollo='"+getIntent().getExtras().getString("tipo")+"'");
                while (rs.next()) {
                    //bandera += rs.getString(1);
                    listacc.add(new clasepesadas(R.drawable.pesada,rs.getString(1),rs.getString(3),rs.getString(4)+"KG",rs.getString(5),rs.getString(6)));
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
            Adaptadorpesadas adaptadorpro=new Adaptadorpesadas(listadetallepesadas.this,s);
            listapesadas.setAdapter(adaptadorpro);
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