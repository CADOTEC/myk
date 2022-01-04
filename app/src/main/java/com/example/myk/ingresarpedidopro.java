package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.Statement;

public class ingresarpedidopro extends AppCompatActivity {
    private TextView gallinastxt;
    private TextView     pollostxt;
    private Button sumar;
    private Button terminar;
    private CheckBox chpollos;
    private CheckBox chgallinas;
    private CheckBox chotros;
    private CheckBox chgallos;
    private CheckBox chpollox;
    private CheckBox chpolloy;
    private EditText peso;
    private EditText njaba;
    private EditText naves;
    private double g=0;
    private double p=0;
    private double jp=0;
    private double jg=0;
    private double cg=0;
    private double cp=0;
private int canp=0;
    String gURL = "jdbc:mysql://";
    String gIP = "34.68.109.84";
    String gPORT = "3306";
    String gDATABASE = "myk";
    String gUSR = "root";
    String gPSW = "6jlkhQAUDD7v6MlJomFm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarpedidopro);
        gallinastxt=(TextView)findViewById(R.id.gallinasingpepro);
        pollostxt=(TextView)findViewById(R.id.polloingpepro);
        peso=(EditText)findViewById(R.id.pesoingprope);
        njaba=(EditText)findViewById(R.id.njabaingprope);
        naves=(EditText)findViewById(R.id.navesingprope);
        sumar=(Button)findViewById(R.id.sumarbtningpepro);
        terminar=(Button)findViewById(R.id.terminarbtningpepro);

        chpollos=(CheckBox)findViewById(R.id.checkBoxpolloingprope);
        chgallinas=(CheckBox)findViewById(R.id.checkBoxgallinaingprope);
     LinearLayout otroslayout=(LinearLayout)findViewById(R.id.esconderlayoutagpepro);

        chotros=(CheckBox)findViewById(R.id.otroscbingpepro);
        chgallos=(CheckBox)findViewById(R.id.chgalloingpepro);
        chpollox=(CheckBox)findViewById(R.id.chpolloxingpepro);
        chpolloy=(CheckBox)findViewById(R.id.chpolloyingpepro);
        otroslayout.setVisibility(View.GONE);
        chotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chotros.isChecked()){
                    otroslayout.setVisibility(View.VISIBLE);
                }else{
                    otroslayout.setVisibility(View.GONE);
                }

                /*chgallinas.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#EF5B2D"));
                peso.setHighlightColor(Color.parseColor("#EF5B2D"));
                peso.setTextColor(Color.parseColor("#EF5B2D"));
                njaba.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setText("6");
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#038C7F"));
                    njaba.setTextColor(Color.parseColor("#038C7F"));
                    naves.setTextColor(Color.parseColor("#038C7F"));
                }*/
            }
        });

        chpollos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
chgallinas.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#EF5B2D"));
                peso.setHighlightColor(Color.parseColor("#EF5B2D"));
                peso.setTextColor(Color.parseColor("#EF5B2D"));
                njaba.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setText("6");
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#038C7F"));
                    njaba.setTextColor(Color.parseColor("#038C7F"));
                    naves.setTextColor(Color.parseColor("#038C7F"));
                }
            }
        });
        chgallinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpollos.setChecked(false);
                peso.setLinkTextColor(Color.parseColor("#F2C12E"));
                peso.setHighlightColor(Color.parseColor("#F2C12E"));
                peso.setTextColor(Color.parseColor("#F2C12E"));
                njaba.setTextColor(Color.parseColor("#F2C12E"));
                naves.setText("10");
                naves.setTextColor(Color.parseColor("#F2C12E"));
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#038C7F"));
                    njaba.setTextColor(Color.parseColor("#038C7F"));
                    naves.setTextColor(Color.parseColor("#038C7F"));
                }
            }
        });
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peso.getText().toString().equals("")==false) {
                    double tem=0;

                    if (chgallinas.isChecked()) {


                        insertarpesada("GALLINA",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());



                        tem=Double.parseDouble(""+peso.getText().toString());
                        jg=jg+(Double.parseDouble(""+njaba.getText().toString()));
                        g = g + tem;
                        cg=cg+Double.parseDouble(""+naves.getText().toString());
                        gallinastxt.setText("" + g);
                        peso.setText("");
                    }else{
                    if (chpollos.isChecked()) {

                        insertarpesada("POLLO",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());
                        tem=Double.parseDouble(""+peso.getText().toString());
                        p = p + tem;
                        jp=jp+(Double.parseDouble(""+njaba.getText().toString()));
                        pollostxt.setText("" + p);
                        cp=cp+Double.parseDouble(""+naves.getText().toString());
                        peso.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR ENTRE POLLO O GALLINA", Toast.LENGTH_SHORT).show();
                    }
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "INGRESE UN PESO", Toast.LENGTH_SHORT).show();
                }
            }
        });
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(g>0||p>0){

                    insertar(""+g,""+p,""+jg,""+jp,""+cg,""+cp,"","","");


                    Intent intent=new Intent(ingresarpedidopro.this,agregarpedidoultimo.class);
                    intent.putExtra("pesogallina", ""+g);
                    intent.putExtra("pesopollos", ""+p);
                    intent.putExtra("jabapollo", ""+jp);
                    intent.putExtra("jabagallina", ""+jg);
                    intent.putExtra("cgallinas", ""+cg);
                    intent.putExtra("cpollos", ""+cp);
                    startActivity(intent);
                    finish();
                }




            }
        });

    }
    private void insertar(String pesogallinas,String pesopollos,String cantidaddejabasgallinas,String cantidaddejabaspollos,String cantidadgallinas,String cantidadpollos,String pesonetogallinas,String pesonetopollos,String pesojaba){
        // Toast.makeText(getApplicationContext(), ""+nombrev+"-"+dniorucv+"-"+direccionv+"-"+estadov, Toast.LENGTH_SHORT).show();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO pesadas(pesogallinas,pesopollos,cantidaddejabasgallinas,cantidaddejabaspollos,cantidadgallinas,cantidadpollos,pesonetogallinas,pesonetopollos,pesojaba) VALUES('"+pesogallinas+"','"+pesopollos+"','"+cantidaddejabasgallinas+"','"+cantidaddejabaspollos+"','"+cantidadgallinas+"','"+cantidadpollos+"','"+pesonetogallinas+"','"+pesonetopollos+"','"+pesojaba+"')");

            ConnexionMySQL.close();
            Toast.makeText(getApplicationContext(), "AGREGADO!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    private void insertarpesada(String gallinaopollo,String peso,String cantidaddejabas,String cantidaddeaves){
        // Toast.makeText(getApplicationContext(), ""+nombrev+"-"+dniorucv+"-"+direccionv+"-"+estadov, Toast.LENGTH_SHORT).show();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO detallepesada(gallinaopollo,peso,cantidaddejabas,cantidaddeaves) VALUES('"+gallinaopollo+"','"+peso+"','"+cantidaddejabas+"','"+cantidaddeaves+"')");

            ConnexionMySQL.close();
            canp=canp+1;
            Toast.makeText(getApplicationContext(), "AGREGADO "+canp, Toast.LENGTH_SHORT).show();

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