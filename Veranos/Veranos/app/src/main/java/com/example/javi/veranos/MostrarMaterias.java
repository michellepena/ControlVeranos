package com.example.michelle.veranos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MostrarMaterias extends AppCompatActivity {
    ListView lista;
    ArrayAdapter<String> adaptador;
    HashMap hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_materias);
        lista = (ListView)findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idd = hashMap.get(((TextView)view).getText().toString()).toString();
                //Toast.makeText(MostrarMaterias.this,idd,Toast.LENGTH_LONG).show();
                Intent inDatos = new Intent(MostrarMaterias.this, Formulario.class);
                inDatos.putExtra("id",idd);
                startActivity(inDatos);


            }



        });
        conectar();
    }
    private void conectar(){
        new AsyncTask<String, Void, String>(){


            @Override
            protected String doInBackground(String ... params) {
                String data = webService.getData("http://campos.esy.es/materias.php");
                return data;
            }

            @Override
            protected void onPostExecute(String data) {
                try {
                    hashMap = new HashMap();
                    JSONObject json  =new JSONObject(data);
                    JSONArray array = json.getJSONArray("veranos");
                    String [] materias = new String[array.length()];
                    for (int i = 0; i< array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        materias[i]=(o.getString("materia") + " - " + o.getString("profesor"));
                        hashMap.put(materias[i],o.getString("id_materia"));
                    }
                    adaptador = new ArrayAdapter<String>(MostrarMaterias.this,android.R.layout.simple_list_item_1,materias);
                    lista.setAdapter(adaptador);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();

    }

}
