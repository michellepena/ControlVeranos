package com.example.michelle.veranos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Formulario extends AppCompatActivity {
    EditText edtNom, edtApe, edtCtrl, edtCar;
    Intent inDatos;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        edtNom = (EditText)findViewById(R.id.edtNom);
        edtApe = (EditText)findViewById(R.id.edtApe);
        edtCtrl = (EditText)findViewById(R.id.edtCtrl);
        edtCar = (EditText)findViewById(R.id.edtCar);
        id =getIntent().getExtras().getString("id");
    }
    public void registrarAlumnos(View v){
        String nombre = edtNom.getText().toString();
        String ape= edtApe.getText().toString();
        String ctrl = edtCtrl.getText().toString();
        String carrera = edtCar.getText().toString();

        if (nombre.isEmpty()||ape.isEmpty()|| ctrl.isEmpty()||carrera.isEmpty()){
            Toast.makeText(Formulario.this,"Todos los campos deben estar llenos",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(Formulario.this, "Enviando", Toast.LENGTH_LONG).show();


        new AsyncTask<String, Void, String>(){


            @Override
            protected String doInBackground(String ... params) {
                String mat =params[0];
                String nom = params[1];
                String ctl = params[2];
                String c = params[3];

                String data = webService.getData("http://campos.esy.es/registro.php?materia="+mat +"&nom="+nom +"&ctrl="+ctl +"&carrera="+c);
                return data;
            }

            @Override
            protected void onPostExecute(String data) {
                try {
                    JSONObject  json= new JSONObject(data);
                    if (json.getString("status").equals("ok")){
                        Toast.makeText(Formulario.this, "Alumno registrado con exito!!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(Formulario.this, "Problema con el servidor", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {

                }

            }
        }.execute(id,nombre+" "+ape,ctrl,carrera);

    }


}

