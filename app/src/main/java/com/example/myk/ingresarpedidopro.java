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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ingresarpedidopro extends AppCompatActivity {
    private TextView gallinastxt;
    private TextView     pollostxt;
    private TextView     gallotxt;
    private TextView     polloxtxt;
    private TextView     polloytxt;
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
    private double go=0;
    private double px=0;
    private double py=0;
    private double jp=0;
    private double jg=0;
    private double jgo=0;
    private double jpx=0;
    private double jpy=0;
    private double cg=0;
    private double cp=0;
    private double cgo=0;
    private double cpx=0;
    private double cpy=0;
    private ListView listapesadas;
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
        gallotxt=(TextView)findViewById(R.id.galloingpepro);
        polloxtxt=(TextView)findViewById(R.id.polloxingpepro);
        polloytxt=(TextView)findViewById(R.id.polloyingpepro);
        peso=(EditText)findViewById(R.id.pesoingprope);
        njaba=(EditText)findViewById(R.id.njabaingprope);
        naves=(EditText)findViewById(R.id.navesingprope);
        sumar=(Button)findViewById(R.id.sumarbtningpepro);
        terminar=(Button)findViewById(R.id.terminarbtningpepro);
        listapesadas=(ListView) findViewById(R.id.listapesadasingpepro);

        chpollos=(CheckBox)findViewById(R.id.checkBoxpolloingprope);
        chgallinas=(CheckBox)findViewById(R.id.checkBoxgallinaingprope);
     LinearLayout otroslayout=(LinearLayout)findViewById(R.id.esconderlayoutagpepro);

        chotros=(CheckBox)findViewById(R.id.otroscbingpepro);
        chgallos=(CheckBox)findViewById(R.id.chgalloingpepro);
        chpollox=(CheckBox)findViewById(R.id.chpolloxingpepro);
        chpolloy=(CheckBox)findViewById(R.id.chpolloyingpepro);
        otroslayout.setVisibility(View.GONE);
        gallotxt.setVisibility(View.GONE);
        polloxtxt.setVisibility(View.GONE);
        polloytxt.setVisibility(View.GONE);
        //mostrar ocultar al presionar checkbox otros
        chotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chotros.isChecked()){
                    //CLICK EN OTROS
                    otroslayout.setVisibility(View.VISIBLE);
                    gallotxt.setVisibility(View.VISIBLE);
                    polloxtxt.setVisibility(View.VISIBLE);
                    polloytxt.setVisibility(View.VISIBLE);
                }else{
                    otroslayout.setVisibility(View.GONE);
                    gallotxt.setVisibility(View.GONE);
                    polloxtxt.setVisibility(View.GONE);
                    polloytxt.setVisibility(View.GONE);
                }
            }
        });
        //fin mostrar ocultar al presionar checkbox otros

        //inicializar checkbox
        chpollos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgallinas.setChecked(false);
                chgallos.setChecked(false);
                chpollox.setChecked(false);
                chpolloy.setChecked(false);

                peso.setLinkTextColor(Color.parseColor("#EF5B2D"));
                peso.setHighlightColor(Color.parseColor("#EF5B2D"));
                peso.setTextColor(Color.parseColor("#EF5B2D"));
                njaba.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setTextColor(Color.parseColor("#EF5B2D"));
                naves.setText("");
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false && chgallos.isChecked()==false && chpollox.isChecked()==false && chpolloy.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njaba.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        chgallinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpollos.setChecked(false);
                chgallos.setChecked(false);
                chpollox.setChecked(false);
                chpolloy.setChecked(false);
                peso.setLinkTextColor(Color.parseColor("#F2C12E"));
                peso.setHighlightColor(Color.parseColor("#F2C12E"));
                peso.setTextColor(Color.parseColor("#F2C12E"));
                njaba.setTextColor(Color.parseColor("#F2C12E"));
                naves.setText("");
                naves.setTextColor(Color.parseColor("#F2C12E"));
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false && chgallos.isChecked()==false && chpollox.isChecked()==false && chpolloy.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njaba.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        chgallos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpollos.setChecked(false);
                chgallinas.setChecked(false);
                chpollox.setChecked(false);
                chpolloy.setChecked(false);
                peso.setLinkTextColor(Color.parseColor("#FF0000"));
                peso.setHighlightColor(Color.parseColor("#FF0000"));
                peso.setTextColor(Color.parseColor("#FF0000"));
                njaba.setTextColor(Color.parseColor("#FF0000"));
                naves.setText("");
                naves.setTextColor(Color.parseColor("#FF0000"));
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false && chgallos.isChecked()==false && chpollox.isChecked()==false && chpolloy.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njaba.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        chpollox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpollos.setChecked(false);
                chgallinas.setChecked(false);
                chgallos.setChecked(false);
                chpolloy.setChecked(false);
                peso.setLinkTextColor(Color.parseColor("#025959"));
                peso.setHighlightColor(Color.parseColor("#025959"));
                peso.setTextColor(Color.parseColor("#025959"));
                njaba.setTextColor(Color.parseColor("#025959"));
                naves.setText("");
                naves.setTextColor(Color.parseColor("#025959"));
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false && chgallos.isChecked()==false && chpollox.isChecked()==false && chpolloy.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njaba.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        chpolloy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpollos.setChecked(false);
                chgallinas.setChecked(false);
                chgallos.setChecked(false);
                chpollox.setChecked(false);
                peso.setLinkTextColor(Color.parseColor("#2d4d9f"));
                peso.setHighlightColor(Color.parseColor("#2d4d9f"));
                peso.setTextColor(Color.parseColor("#2d4d9f"));
                njaba.setTextColor(Color.parseColor("#2d4d9f"));
                naves.setText("");
                naves.setTextColor(Color.parseColor("#2d4d9f"));
                if(chgallinas.isChecked()==false && chpollos.isChecked()==false && chgallos.isChecked()==false && chpollox.isChecked()==false && chpolloy.isChecked()==false){
                    peso.setTextColor(Color.parseColor("#5BE7F7"));
                    njaba.setTextColor(Color.parseColor("#5BE7F7"));
                    naves.setTextColor(Color.parseColor("#5BE7F7"));
                }
            }
        });
        //fininiocializar checkbox

        //boton sumar pesada
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peso.getText().toString().equals("")==false && naves.getText().toString().equals("")==false && njaba.getText().toString().equals("")==false) {
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
                        if (chgallos.isChecked()) {

                            insertarpesada("GALLO",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());
                            tem=Double.parseDouble(""+peso.getText().toString());
                            go = go + tem;
                            jgo=jgo+(Double.parseDouble(""+njaba.getText().toString()));
                            gallotxt.setText("" + go);
                            cgo=cgo+Double.parseDouble(""+naves.getText().toString());
                            peso.setText("");
                        }else {
                            if (chpollox.isChecked()) {

                                insertarpesada("POLLO X",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());
                                tem=Double.parseDouble(""+peso.getText().toString());
                                px = px + tem;
                                jpx=jpx+(Double.parseDouble(""+njaba.getText().toString()));
                                polloxtxt.setText("" + px);
                                cpx=cpx+Double.parseDouble(""+naves.getText().toString());
                                peso.setText("");
                            }else {
                                if (chpolloy.isChecked()) {
                                    insertarpesada("POLLO Y",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());
                                    tem=Double.parseDouble(""+peso.getText().toString());
                                    py = py + tem;
                                    jpy=jpy+(Double.parseDouble(""+njaba.getText().toString()));
                                    polloytxt.setText("" + py);
                                    cpy=cpy+Double.parseDouble(""+naves.getText().toString());
                                    peso.setText("");
                                }else {
                                    Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR ENTRE POLLO, GALLINA U OTROS", Toast.LENGTH_SHORT).show();
                                }

                                 }
                        }
                    }
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "INGRESE UN PESO", Toast.LENGTH_SHORT).show();
                }
                llenarlistview();
            }
        });
        //finboton sumar pesadas

        //pasar al sioguiente activity, boton terminar
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
        //FIN pasar al sioguiente activity, boton terminar
    }

    //llenar  listview de pesadas
    private void llenarlistview(){
        listapesadas.setAdapter(null);
        ArrayList<clasepesadas> listacc=new ArrayList<clasepesadas>();


        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("Select * from detallepesada where idcompra=0");


            while (rs.next()) {
                //bandera += rs.getString(1);
                listacc.add(new clasepesadas(R.drawable.pesada,rs.getString(1),rs.getString(3),rs.getString(4)+"KG",rs.getString(5),rs.getString(6)));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        Adaptadorpesadas adaptadorpro=new Adaptadorpesadas(ingresarpedidopro.this,listacc);
        listapesadas.setAdapter(adaptadorpro);
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