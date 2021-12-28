package com.example.myk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class agregarpedidoultimo extends AppCompatActivity {
    private TextView pbga,pbpo,prga,prpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarpedidoultimo);
        pbga=(TextView)findViewById(R.id.pesobrutogaagpeul);
        pbpo=(TextView)findViewById(R.id.pesobrutopoagpeul);
        prga=(TextView)findViewById(R.id.pesorealgaagpeul);
        prpo=(TextView)findViewById(R.id.pesorealpoagpeul);

        String valorg = getIntent().getExtras().getString("pesogallina");
        String valorp = getIntent().getExtras().getString("pesopollos");

        String valorcp = getIntent().getExtras().getString("cpollos");
        String valorcg = getIntent().getExtras().getString("cgallinas");

        String jp = getIntent().getExtras().getString("jabapollo");
        String jg = getIntent().getExtras().getString("jabagallina");
        pbga.setText(""+valorg);
        pbpo.setText(""+valorp);

        double pesoga=0;
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
        prpo.setText(""+japo);
    }
}