
package com.mycompany.practica1.cargaarchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JTextArea;

public class Leer {
    private static File Archivo;
    
    public static void LeerArchivo(String Direccion, JTextArea Espacio){
        try{
        Archivo = new File(Direccion);
        FileReader fr = new FileReader(Archivo);
        BufferedReader br = new BufferedReader(fr);
        String Linea;
        while((Linea = br.readLine()) != null){
         Espacio.setText(Linea);
        }
        }
        catch(Exception e){
        }
    
    }
    
}
