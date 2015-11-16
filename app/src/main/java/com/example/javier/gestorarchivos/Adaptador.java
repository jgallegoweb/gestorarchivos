package com.example.javier.gestorarchivos;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Javier on 07/11/2015.
 */
public class Adaptador extends ArrayAdapter<File>{
    private Context context;
    private int resource;
    private LayoutInflater layoutInflater;
    private ArrayList<File> carpetas;

    static class ViewHolder{
        public ImageView ivTipo;
        public TextView tvNombre, tvPermisos;
        public ImageButton ibAccion;
    }

    public Adaptador(Context context, int resource, ArrayList<File> carpetas) {
        super(context, resource, carpetas);
        this.context = context;
        this.resource = resource;
        this.carpetas = carpetas;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v = new ViewHolder();
        File file = carpetas.get(position);
        if(convertView==null){
            convertView = layoutInflater.inflate(resource, null);
            v.tvNombre = (TextView)convertView.findViewById(R.id.tvNombre);
            v.tvPermisos = (TextView)convertView.findViewById(R.id.tvPermisos);
            v.ibAccion = (ImageButton)convertView.findViewById(R.id.ibAccion);
            v.ivTipo = (ImageView)convertView.findViewById(R.id.ivTipo);
            convertView.setTag(v);
        }else{
            v = (ViewHolder)convertView.getTag();
        }
        String permisos = "";
        permisos += (file.canRead()) ? "r" : "";
        permisos += (file.canWrite()) ? "w" : "";
        permisos += (file.canExecute()) ? "x" : "";
        v.tvNombre.setText(file.getName());
        v.tvPermisos.setText(permisos);
        int img, imgbt;
        if(file.isDirectory()){
            img = R.drawable.ic_action;
            imgbt = R.drawable.ic_ir;
        }else{
            img = R.drawable.ic_file;
            imgbt = R.drawable.ic_ver;
        }
        v.ibAccion.setTag(position);
        v.ivTipo.setImageResource(img);
        v.ibAccion.setImageResource(imgbt);
        if(!file.canRead()){
            v.ibAccion.setVisibility(View.GONE);
        }else{
            v.ibAccion.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
