package com.example.michelle.veranos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {
    Intent inMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    public void atividaMate(View v){
        inMateria = new Intent(this,Materia.class);
        startActivity(inMateria);
    }
    public void mostrarMaterias(View v){
        inMateria = new Intent(this,MostrarMaterias.class);
        startActivity(inMateria);

    }
}
