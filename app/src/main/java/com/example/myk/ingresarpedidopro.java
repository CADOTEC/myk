package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private String idproveedor;
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
      idproveedor=getIntent().getExtras().getString("idproveedor");
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
       // llenarlistview();
        listapesadas.setAdapter(null);
        new task2().execute();
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


                    if (chpolloy.isChecked()||chgallinas.isChecked()||chpollox.isChecked()||chgallos.isChecked()||chpollos.isChecked()) {
                        listapesadas.setAdapter(null);
                        new task1().execute("hola");
                         }else{
                        Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR ENTRE POLLO, GALLINA U OTROS", Toast.LENGTH_SHORT).show();

                    }



                }else{
                    Toast.makeText(getApplicationContext(), "INGRESE UN PESO", Toast.LENGTH_SHORT).show();
                }
               // llenarlistview();



            }

        });
        //finboton sumar pesadas

        //pasar al sioguiente activity, boton terminar
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(g>0||p>0||go>0||px>0||py>0){

                    Intent intent=new Intent(ingresarpedidopro.this,agregarpedidoultimo.class);
                    //transfiriendo pesos
                    intent.putExtra("tgallinas", ""+g);
                    intent.putExtra("tpollos", ""+p);
                    intent.putExtra("tgallo", ""+go);
                    intent.putExtra("tpollox", ""+px);
                    intent.putExtra("tpolloy", ""+py);

                    //transfiriendo cantidad de jabas
                    intent.putExtra("jg", ""+jg);
                    intent.putExtra("jp", ""+jp);
                    intent.putExtra("jgo", ""+jgo);
                    intent.putExtra("jpx", ""+jpx);
                    intent.putExtra("jpy", ""+jpy);

                    //transfiriendo cantidad de aves
                    intent.putExtra("cg", ""+cg);
                    intent.putExtra("cp", ""+cp);
                    intent.putExtra("cgo", ""+cgo);
                    intent.putExtra("cpx", ""+cpx);
                    intent.putExtra("cpy", ""+cpy);
                    intent.putExtra("idproveedor", ""+idproveedor);
                    startActivity(intent);
                    finish();
                }




            }
        });
        //FIN pasar al sioguiente activity, boton terminar

        //seleccionar elemento de la lista
        listapesadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                view.setBackgroundColor(Color.parseColor("#038C7F"));
                clasepesadas selItem = (clasepesadas) listapesadas.getItemAtPosition(position);

                Intent intent=new Intent(ingresarpedidopro.this, detallepesadalista.class);
                intent.putExtra("DATO", ""+selItem.getId());
                startActivity(intent);
                //finish();
                // CustomDialogClass cdd = new CustomDialogClass(numeros.this,""+selItem.getCantidad(),""+selItem.getId(),listanumeros,"Cantidad");

                //  cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // cdd.show();
                Handler handler = new Handler(); handler.postDelayed(new Runnable() { public void run() { progressDialog.dismiss(); } }, 2000); // 3000 milliseconds delay

            }
        });
        //FIN seleccionar elementop de la lista
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

            rs = st.executeQuery("SELECT  SUM(peso), gallinaopollo AS TIPO,SUM(cantidaddejabas),SUM(cantidaddeaves) FROM detallepesada WHERE idcompra=0 GROUP BY TIPO;");
            while (rs.next()) {

                if(rs.getString(2).equals("GALLINA")){
                    gallinastxt.setText(rs.getString(1));
                    g=Double.parseDouble(rs.getString(1));
                    jg=Double.parseDouble(rs.getString(3));
                    cg=Double.parseDouble(rs.getString(4));
                }
                if(rs.getString(2).equals("POLLO")){
                    pollostxt.setText(rs.getString(1));
                    jp=Double.parseDouble(rs.getString(3));
                    cp=Double.parseDouble(rs.getString(4));
                    p=Double.parseDouble(rs.getString(1));
                }
                if(rs.getString(2).equals("POLLO X")){
                    polloxtxt.setText(rs.getString(1));
                    jpx=Double.parseDouble(rs.getString(3));
                    cpx=Double.parseDouble(rs.getString(4));
                    px=Double.parseDouble(rs.getString(1));
                }
                if(rs.getString(2).equals("POLLO Y")){
                    polloytxt.setText(rs.getString(1));
                    jpy=Double.parseDouble(rs.getString(3));
                    cpy=Double.parseDouble(rs.getString(4));
                    py=Double.parseDouble(rs.getString(1));
                }
                if(rs.getString(2).equals("GALLO")){
                    gallotxt.setText(rs.getString(1));
                    jgo=Double.parseDouble(rs.getString(3));
                    cgo=Double.parseDouble(rs.getString(4));
                    go=Double.parseDouble(rs.getString(1));
                }


            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        Adaptadorpesadas adaptadorpro=new Adaptadorpesadas(ingresarpedidopro.this,listacc);
        listapesadas.setAdapter(adaptadorpro);
    }
    private class task1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(ingresarpedidopro.this);
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.progress_dialog);
        }

        @Override
        protected String doInBackground(String... strings) {
            if (chgallinas.isChecked()) {
                insertarpesada("GALLINA",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());

            }else{
                if (chpollos.isChecked()) {

                    insertarpesada("POLLO",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());


                }else{
                    if (chgallos.isChecked()) {

                        insertarpesada("GALLO",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());


                    }else {
                        if (chpollox.isChecked()) {

                            insertarpesada("POLLO X",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());

                        }else {
                            if (chpolloy.isChecked()) {
                                insertarpesada("POLLO Y",""+peso.getText().toString(),""+njaba.getText().toString(),""+naves.getText().toString());


                            }else {
                            }

                        }
                    }
                }
            }
            return strings[0];
        }



        @Override
        protected void onPostExecute(String s) {
            llenarlistview();
            peso.setText("");
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "AGREGADO", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }




    private class task2 extends AsyncTask<Void,Integer, ArrayList<clasepesadas>> {
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(ingresarpedidopro.this);
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

                ResultSet rs = st.executeQuery("Select * from detallepesada where idcompra=0");
                while (rs.next()) {
                    //bandera += rs.getString(1);
                    listacc.add(new clasepesadas(R.drawable.pesada,rs.getString(1),rs.getString(3),rs.getString(4)+"KG",rs.getString(5),rs.getString(6)));
                }
                rs.close();

                rs = st.executeQuery("SELECT  SUM(peso), gallinaopollo AS TIPO,SUM(cantidaddejabas),SUM(cantidaddeaves) FROM detallepesada WHERE idcompra=0 GROUP BY TIPO;");
                while (rs.next()) {

                    if(rs.getString(2).equals("GALLINA")){
                        g=Double.parseDouble(rs.getString(1));
                        jg=Double.parseDouble(rs.getString(3));
                        cg=Double.parseDouble(rs.getString(4));
                    }
                    if(rs.getString(2).equals("POLLO")){

                        jp=Double.parseDouble(rs.getString(3));
                        cp=Double.parseDouble(rs.getString(4));
                        p=Double.parseDouble(rs.getString(1));
                    }
                    if(rs.getString(2).equals("POLLO X")){

                        jpx=Double.parseDouble(rs.getString(3));
                        cpx=Double.parseDouble(rs.getString(4));
                        px=Double.parseDouble(rs.getString(1));
                    }
                    if(rs.getString(2).equals("POLLO Y")){

                        jpy=Double.parseDouble(rs.getString(3));
                        cpy=Double.parseDouble(rs.getString(4));
                        py=Double.parseDouble(rs.getString(1));
                    }
                    if(rs.getString(2).equals("GALLO")){

                        jgo=Double.parseDouble(rs.getString(3));
                        cgo=Double.parseDouble(rs.getString(4));
                        go=Double.parseDouble(rs.getString(1));
                    }


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
            progressDialog.dismiss();
            gallinastxt.setText(""+g);
            pollostxt.setText(""+p);
            polloxtxt.setText(""+px);
            polloytxt.setText(""+py);
            gallotxt.setText(""+go);
            Adaptadorpesadas adaptadorpro=new Adaptadorpesadas(ingresarpedidopro.this,s);
            listapesadas.setAdapter(adaptadorpro);
            progressDialog.dismiss();
        }
    }


    private void insertar(String peso,String cantidaddejabas,String cantidadaves,String tipo,String idcompra){

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            st.executeUpdate("INSERT INTO pesadas(peso,cantidaddejabas,cantidadaves,tipo,idcompra) VALUES('"+peso+"','"+cantidaddejabas+"','"+cantidadaves+"','"+tipo+"',0)");

            ConnexionMySQL.close();

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