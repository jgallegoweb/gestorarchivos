package com.example.javier.gestorarchivos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private Adaptador adaptador;
    private ListView lvPrincipal;
    private ArrayList<String> directorios;
    private ArrayList<File> archivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onBackPressed() {
        up();
    }

    /***********************************************************************************************
     *** CÓDIGO PROPIO *****************************************************************************
     **********************************************************************************************/

    private void init(){
        directorios = new ArrayList<>();
        directorios.add("/");
        lvPrincipal = (ListView)findViewById(R.id.lvPrincipal);
        mostrarLista();
    }

    private void mostrarLista(){
        setTitle(directorios.get(directorios.size() - 1));
        archivos = this.getArchivos(directorios.get(directorios.size() - 1));
        Collections.sort(archivos, GestionArchivo.getComparator());
        adaptador = new Adaptador(this, R.layout.item, archivos);
        lvPrincipal.setAdapter(adaptador);
        lvPrincipal.setTag(archivos);
    }

    private static ArrayList<File> getArchivos(String ruta){
        File f = new File(ruta);
        File lista[] = f.listFiles();
        ArrayList<File> al = new ArrayList<>();
        for(File fichero : lista){
            al.add(fichero);
        }
        return al;
    }

    public void accionarItem(View v){
        int pos = (int)v.getTag();
        File f = archivos.get(pos);
        if(f.isDirectory()){
            directorios.add(f.getAbsolutePath()+"/");
            Log.v("holita", f.getAbsolutePath());
            mostrarLista();
            adaptador.notifyDataSetChanged();
        }else if(f.isFile()){
            Intent intent = new Intent(this, ActividadLector.class);
            Bundle p = new Bundle();
            p.putSerializable("texto", GestionArchivo.getContenido(f));
            intent.putExtras(p);
            startActivity(intent);
        }
    }

    public void subir(View v){
        up();
    }
    private void up(){
        if(directorios.size()>1)
            directorios.remove(directorios.size() - 1);
        mostrarLista();
    }
}
