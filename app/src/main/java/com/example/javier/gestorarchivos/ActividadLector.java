package com.example.javier.gestorarchivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActividadLector extends AppCompatActivity {

    private TextView tvLector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lector);
        init();
    }

    private void init(){
        tvLector = (TextView)findViewById(R.id.tvLector);
        tvLector.setText((String)getIntent().getExtras().getString("texto"));
    }
}
