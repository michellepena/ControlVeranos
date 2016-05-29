package com.example.michelle.veranos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Materia extends AppCompatActivity {
    EditText edtMateria, edtProfe, edtCosto, edtMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtMateria=(EditText)findViewById(R.id.edtMateria);
        edtProfe =(EditText)findViewById(R.id.edtCosto);
        edtCosto = (EditText)findViewById(R.id.edtCosto);
        edtMax = (EditText)findViewById(R.id.edtMax);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void registrarMat(View v){
        String materia = edtMateria.getText().toString();
        String profe= edtProfe.getText().toString();
        String costo = edtCosto.getText().toString();
        String limite = edtMax.getText().toString();

        new AsyncTask<String, Void, String>(){


            @Override
            protected String doInBackground(String ... params) {
                String m =params[0];
                String p = params[1];
                String c = params[2];
                String l = params[3];
                String data = webService.getData("http://campos.esy.es/alta.php?mat="+m +"&profe="+p +"&cost="+c +"&lim="+l);
                return data;
            }

            @Override
            protected void onPostExecute(String data) {
                Toast.makeText(Materia.this,data,Toast.LENGTH_LONG).show();
            }
        }.execute(materia,profe,costo,limite);

    }


}
