package com.example.javier.gestorarchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Javier on 08/11/2015.
 */
public class GestionArchivo {
    public static Comparator<File> getComparator(){
        Comparator<File> c = new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                if(lhs.isFile() && !rhs.isFile()){
                    return -1;
                }else if(!lhs.isFile() && rhs.isFile()){
                    return 1;
                }
                return lhs.getName().compareTo(rhs.getName());
            }
        };
        return c;
    }
    public static String getContenido(File file){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String total = "";
        String linea;
        try {
            while ((linea = br.readLine()) != null) {
                total+=linea+"\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static ArrayList<File> getArchivos(String ruta){
        File f = new File(ruta);
        File lista[] = f.listFiles();
        ArrayList<File> al = new ArrayList<>();
        for(File fichero : lista){
            al.add(fichero);
        }
        return al;
    }
}
